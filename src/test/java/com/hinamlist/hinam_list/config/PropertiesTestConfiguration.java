package com.hinamlist.hinam_list.config;

import com.hinamlist.hinam_list.service.json_producer.JsonProducer;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class PropertiesTestConfiguration {
    @Bean
    JsonProducer HaziHinamProducer() {
        return Mockito.mock(JsonProducer.class);
    }

    @Bean
    JsonProducer RamiLeviProducer() {
        return Mockito.mock(JsonProducer.class);
    }
}
