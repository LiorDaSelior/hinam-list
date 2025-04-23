package com.hinamlist.hinam_list.service.feature.main_table;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hinamlist.hinam_list.model.json_consumer.main_table.MainTableProduct;
import com.hinamlist.hinam_list.service.feature.Feature;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainTableFeatureConfig {
    @Bean
    public Feature<MainTableProduct> MainTableFeature(
            RabbitAdmin rabbitAdmin,
            @Qualifier("SimpleTemplate") RabbitTemplate supplierRabbitTemplate,
            @Qualifier("JacksonTemplate") RabbitTemplate featureRabbitTemplate,
            TopicExchange mainTopicExchange,
            ObjectMapper objectMapper,
            MainTableFeatureLogic mainTableFeatureLogic,
            MainTableStoreResponse mainTableStoreResponse
    ) {
        return new Feature<>(MainTableProduct.class,
                rabbitAdmin, supplierRabbitTemplate, featureRabbitTemplate,
                mainTopicExchange, objectMapper,
                mainTableFeatureLogic,
                mainTableStoreResponse.getStoreResponseMap(),
                "main.#"
                );
    }
}
