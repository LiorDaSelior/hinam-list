package com.hinamlist.hinam_list.service.json_producer;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class JsonProducerExchange {

    @Bean
    TopicExchange MainJsonProducerExchange(@Value("${rabbitmq.main-json-producer.exchange.name}") String mainJsonProducerExchangeName) {
        return new TopicExchange(mainJsonProducerExchangeName);
    }
}
