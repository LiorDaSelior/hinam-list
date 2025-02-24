package com.hinamlist.hinam_list;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ExchangeFactory {

    @Bean
    FanoutExchange jsonSenderHaziHinamExchange(@Value("${rabbitmq.json-sender.exchange.hazihinam}")  String jsonSenderExchangeName) {
        return new FanoutExchange(jsonSenderExchangeName);
    }

    @Bean
    FanoutExchange jsonSenderRamiLeviExchange(@Value("${rabbitmq.json-sender.exchange.ramilevi}")  String jsonSenderExchangeName) {
        return new FanoutExchange(jsonSenderExchangeName);
    }

    @Bean
    FanoutExchange jsonSenderCarrfourExchange(@Value("${rabbitmq.json-sender.exchange.carrfour}")  String jsonSenderExchangeName) {
        return new FanoutExchange(jsonSenderExchangeName);
    }
}
