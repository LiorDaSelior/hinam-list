package com.hinamlist.hinam_list.service.data_scraping.json_producer;

import com.hinamlist.hinam_list.service.data_scraping.json_scraper.HaziHinamJsonScraper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HaziHinamProducer extends JsonProducer {
    public HaziHinamProducer(RabbitTemplate rabbitTemplate, HaziHinamJsonScraper scraper, @Value("${rabbitmq.json-sender.exchange.hazihinam}") String jsonSenderExchangeName) {
        super(rabbitTemplate, scraper, jsonSenderExchangeName);
    }
}
