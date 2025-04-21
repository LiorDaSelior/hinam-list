package com.hinamlist.hinam_list.service.feature;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class Feature<T> {
    protected Queue featureQueue;
    protected FeatureSupplier<T> featureSupplier;
    protected Consumer<T> featureLogic;
    protected ObjectMapper objectMapper;
    protected TypeReference<T> jacksonTypeRef;

    public Feature(RabbitAdmin rabbitAdmin,
                   RabbitTemplate rabbitTemplate,
                   TopicExchange mainTopicExchange,
                   ObjectMapper objectMapper,
                   Consumer<T> featureLogic,
                   Map<String, Function<JSONObject, T>> storeResponseMap,
                   String... topics) {
        this.jacksonTypeRef = new TypeReference<T>() {};
        this.featureQueue = rabbitAdmin.declareQueue();
        this.featureSupplier = new FeatureSupplier<>(rabbitAdmin, rabbitTemplate,
                this.featureQueue.getName(), mainTopicExchange, storeResponseMap, topics);
        this.featureLogic = featureLogic;
        this.objectMapper = objectMapper;

        connectToQueue(rabbitAdmin, featureQueue);
    }


    private void connectToQueue(RabbitAdmin admin, Queue queue) {
        SimpleMessageListenerContainer listener = new SimpleMessageListenerContainer(admin.getRabbitTemplate().getConnectionFactory());
        listener.addQueues(queue);
        listener.setMessageListener(msg -> {
            T featureClass;
            try {
                featureClass = objectMapper.readValue(msg.getBody(), jacksonTypeRef);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            featureLogic.accept(featureClass);
        });
        listener.start();
    }
}
