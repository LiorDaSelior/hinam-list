package com.hinamlist.hinam_list.service.json_producer;

import com.hinamlist.hinam_list.service.json_scraper.CarrfourJsonScraper;
import com.hinamlist.hinam_list.service.json_scraper.HaziHinamJsonScraper;
import com.hinamlist.hinam_list.service.json_scraper.RamiLeviJsonScraper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonProducerConfig {

    @Bean
    public JsonProducer HaziHinamProducer(
            RabbitTemplate rabbitTemplate,
            HaziHinamJsonScraper scraper,
            @Value("${rabbitmq.json-sender.exchange.hazihinam}") String jsonSenderExchangeName) {
        return new JsonProducer(rabbitTemplate, scraper, jsonSenderExchangeName);
    }

    @Bean
    public JsonProducer RamiLeviProducer(
            RabbitTemplate rabbitTemplate,
            RamiLeviJsonScraper scraper,
            @Value("${rabbitmq.json-sender.exchange.ramilevi}") String jsonSenderExchangeName) {
        return new JsonProducer(rabbitTemplate, scraper, jsonSenderExchangeName);
    }

    @Bean
    public JsonProducer CarrfourProducer(
            RabbitTemplate rabbitTemplate,
            CarrfourJsonScraper scraper,
            @Value("${rabbitmq.json-sender.exchange.carrfour}") String jsonSenderExchangeName) {
        return new JsonProducer(rabbitTemplate, scraper, jsonSenderExchangeName);
    }
}
