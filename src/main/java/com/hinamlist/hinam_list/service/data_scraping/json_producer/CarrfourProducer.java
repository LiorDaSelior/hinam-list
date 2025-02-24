package com.hinamlist.hinam_list.service.data_scraping.json_producer;

import com.hinamlist.hinam_list.service.data_scraping.json_scraper.CarrfourJsonScraper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CarrfourProducer extends JsonProducer {
    public CarrfourProducer(RabbitTemplate rabbitTemplate, CarrfourJsonScraper scraper, @Value("${rabbitmq.json-sender.exchange.carrfour}") String jsonSenderExchangeName) {
        super(rabbitTemplate, scraper, jsonSenderExchangeName);
    }
}
