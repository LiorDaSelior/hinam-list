package com.hinamlist.hinam_list.service.json_producer;

import com.hinamlist.hinam_list.config.StoreDataConfigProperties;
import com.hinamlist.hinam_list.service.json_scraper.CarrfourJsonScraper;
import com.hinamlist.hinam_list.service.json_scraper.HaziHinamJsonScraper;
import com.hinamlist.hinam_list.service.json_scraper.RamiLeviJsonScraper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonProducerConfig {

    @Bean
    public JsonProducer HaziHinamMainJsonProducer(
            @Qualifier("SimpleTemplate") RabbitTemplate rabbitTemplate,
            HaziHinamJsonScraper scraper,
            StoreDataConfigProperties storeDataConfigProperties) {
        return new JsonProducer(rabbitTemplate, scraper, "HaziHinam", storeDataConfigProperties);
    }

    @Bean
    public JsonProducer RamiLeviMainJsonProducer(
            @Qualifier("SimpleTemplate") RabbitTemplate rabbitTemplate,
            RamiLeviJsonScraper scraper,
            StoreDataConfigProperties storeDataConfigProperties) {
        return new JsonProducer(rabbitTemplate, scraper, "RamiLevi", storeDataConfigProperties);
    }

    @Bean
    public JsonProducer CarrfourMainJsonProducer(
            @Qualifier("SimpleTemplate") RabbitTemplate rabbitTemplate,
            CarrfourJsonScraper scraper,
            StoreDataConfigProperties storeDataConfigProperties) {
        return new JsonProducer(rabbitTemplate, scraper, "Carrfour", storeDataConfigProperties);
    }
}
