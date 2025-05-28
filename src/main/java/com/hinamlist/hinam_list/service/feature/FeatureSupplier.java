package com.hinamlist.hinam_list.service.feature;

import com.hinamlist.hinam_list.service.json_producer.ProducerMessageTypeEnum;
import org.json.JSONObject;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class FeatureSupplier<T> {
    protected RabbitAdmin rabbitAdmin;
    protected RabbitTemplate supplierRabbitTemplate;
    protected RabbitTemplate featureRabbitTemplate;
    protected String featureQueueName;
    protected Queue consumerQueue;
    protected FeatureExtractor<T> featureExtractor;
    protected SimpleMessageListenerContainer listenerContainer;

    public FeatureSupplier(RabbitAdmin rabbitAdmin,
                           RabbitTemplate supplierRabbitTemplate,
                           RabbitTemplate featureRabbitTemplate,
                           String featureQueueName,
                           TopicExchange mainTopicExchange,
                           FeatureExtractor<T> featureExtractor,
                           String... topics
                           ) {
        this.rabbitAdmin = rabbitAdmin;
        this.supplierRabbitTemplate = supplierRabbitTemplate;
        this.featureRabbitTemplate = featureRabbitTemplate;
        this.featureQueueName = featureQueueName;
        this.featureExtractor = featureExtractor;
        this.consumerQueue = rabbitAdmin.declareQueue();
        connectToTopicExchange(mainTopicExchange, topics);
    }

    protected void connectToTopicExchange(TopicExchange exchange,
                                          String... topics) {
        for (String topic : topics) {
            rabbitAdmin.declareBinding(BindingBuilder.bind(consumerQueue).to(exchange).with(topic));
        }
        listenerContainer = new SimpleMessageListenerContainer(this.supplierRabbitTemplate.getConnectionFactory());
        listenerContainer.addQueues(consumerQueue);
        listenerContainer.setMessageListener(this::handleMessage);
        listenerContainer.start();
    }

    protected void handleMessage(Message msg) {
        MessageProperties msgProperties = msg.getMessageProperties();
        String storeName = msgProperties.getHeader("store");
        ProducerMessageTypeEnum messageType =  ProducerMessageTypeEnum.valueOf(msgProperties.getHeader("messageType"));
        if (messageType == ProducerMessageTypeEnum.DATA) {
            T res = featureExtractor.extractFromJsonDataByStore(
                    new JSONObject(new String(msg.getBody(), StandardCharsets.UTF_8)),
                    storeName
            );
            featureRabbitTemplate.convertAndSend(featureQueueName, res);
        }
    }
}
