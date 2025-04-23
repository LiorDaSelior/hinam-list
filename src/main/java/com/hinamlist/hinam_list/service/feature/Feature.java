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
    protected TypeReference<T> jacksonTypeRef;

    public Feature( Class<T> featureClass,
                    RabbitAdmin rabbitAdmin,
                   RabbitTemplate supplierRabbitTemplate,
                   RabbitTemplate featureRabbitTemplate,
                   TopicExchange mainTopicExchange,
                   ObjectMapper objectMapper,
                   Consumer<T> featureLogic,
                   Map<String, Function<JSONObject, T>> storeResponseMap,
                   String... topics) {
        this.featureClass = featureClass;
        this.jacksonTypeRef = new TypeReference<T>() {};
        this.featureQueue = rabbitAdmin.declareQueue();
        this.featureSupplier = new FeatureSupplier<>(rabbitAdmin, supplierRabbitTemplate, featureRabbitTemplate,
                this.featureQueue.getName(), mainTopicExchange, storeResponseMap, topics);
        this.featureLogic = featureLogic;
        this.objectMapper = objectMapper;

        connectToQueue(featureRabbitTemplate, featureQueue);
    }


    private void connectToQueue(RabbitTemplate featureRabbitTemplate, Queue queue) {
        SimpleMessageListenerContainer listener = new SimpleMessageListenerContainer(featureRabbitTemplate.getConnectionFactory());
        listener.addQueues(queue);
        listener.setMessageListener(msg -> {
            T featureObject;
            try {
                featureObject = objectMapper.readValue(msg.getBody(), this.featureClass);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            featureLogic.accept(featureObject);
        });
        listener.start();
    }
}
