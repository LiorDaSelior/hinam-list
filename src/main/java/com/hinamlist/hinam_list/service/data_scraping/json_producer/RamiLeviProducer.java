package com.hinamlist.hinam_list.service.data_scraping.json_producer;

import com.hinamlist.hinam_list.service.data_scraping.json_scraper.RamiLeviJsonScraper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RamiLeviProducer extends JsonProducer {
    public RamiLeviProducer(RabbitTemplate rabbitTemplate, RamiLeviJsonScraper scraper, @Value("${rabbitmq.json-sender.exchange.ramilevi}") String jsonSenderExchangeName) {
        super(rabbitTemplate, scraper, jsonSenderExchangeName);
    }
}
