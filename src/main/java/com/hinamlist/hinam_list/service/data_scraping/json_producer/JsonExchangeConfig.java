package com.hinamlist.hinam_list.service.data_scraping.json_producer;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JsonExchangeConfig {

    @Bean
    @Qualifier("HaziHinamExchange")
    FanoutExchange jsonSenderHaziHinamExchange(@Value("${rabbitmq.json-sender.exchange.hazihinam}")  String jsonSenderExchangeName) {
        return new FanoutExchange(jsonSenderExchangeName);
    }

    @Bean
    @Qualifier("RamiLeviExchange")
    FanoutExchange jsonSenderRamiLeviExchange(@Value("${rabbitmq.json-sender.exchange.ramilevi}")  String jsonSenderExchangeName) {
        return new FanoutExchange(jsonSenderExchangeName);
    }

    @Bean
    @Qualifier("CarrfourExchange")
    FanoutExchange jsonSenderCarrfourExchange(@Value("${rabbitmq.json-sender.exchange.carrfour}")  String jsonSenderExchangeName) {
        return new FanoutExchange(jsonSenderExchangeName);
    }
}
