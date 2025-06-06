package com.hinamlist.hinam_list.service.feature;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class Feature<T> {
    protected Class<T> featureClass;
    protected Queue featureQueue;
    protected FeatureSupplier<T> featureSupplier;
    protected Consumer<T> featureLogic;
    protected ObjectMapper objectMapper;
    protected SimpleMessageListenerContainer listenerContainer;

    public Feature( Class<T> featureClass,
                    RabbitAdmin rabbitAdmin,
                   RabbitTemplate supplierRabbitTemplate,
                   RabbitTemplate featureRabbitTemplate,
                   TopicExchange mainTopicExchange,
                   ObjectMapper objectMapper,
                   Consumer<T> featureLogic,
                   FeatureExtractor<T> featureExtractor,
                   String... topics) {
        this.featureClass = featureClass;
        this.featureQueue = rabbitAdmin.declareQueue();
        this.featureSupplier = new FeatureSupplier<>(rabbitAdmin, supplierRabbitTemplate, featureRabbitTemplate,
                this.featureQueue.getName(), mainTopicExchange, featureExtractor, topics);
        this.featureLogic = featureLogic;
        this.objectMapper = objectMapper;

        connectToQueue(featureRabbitTemplate, featureQueue);
    }


    private void connectToQueue(RabbitTemplate featureRabbitTemplate, Queue queue) {
        listenerContainer = new SimpleMessageListenerContainer(featureRabbitTemplate.getConnectionFactory());
        listenerContainer.addQueues(queue);
        listenerContainer.setMessageListener(msg -> {
            T featureObject;
            try {
                featureObject = objectMapper.readValue(msg.getBody(), this.featureClass);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            featureLogic.accept(featureObject);
        });
        listenerContainer.start();
    }
}
