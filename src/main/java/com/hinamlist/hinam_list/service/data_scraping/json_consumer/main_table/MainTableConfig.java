package com.hinamlist.hinam_list.service.data_scraping.json_consumer.main_table;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MainTableConfig {
    @Bean
    @Qualifier("${rabbitmq.main-table-collector.exchange}")
    DirectExchange MainTableCollectorExchange(@Value("${rabbitmq.main-table-collector.exchange}")  String collectorExchangeName) {
        return new DirectExchange(collectorExchangeName);
    }

    @Bean
    @Qualifier("${rabbitmq.main-table-collector.queue}")
    Queue MainTableCollectorQueue(@Value("${rabbitmq.main-table-collector.queue}")  String collectorQueueName) {
        return new Queue(collectorQueueName);
    }

    @Bean
    public Binding MainTableCollectorBinding(@Qualifier("${rabbitmq.main-table-collector.exchange}") DirectExchange collectorExchange,
                                             @Qualifier("${rabbitmq.main-table-collector.queue}") Queue collectorQueue) {
        return BindingBuilder.bind(collectorQueue).to(collectorExchange).with("");
    }

}
