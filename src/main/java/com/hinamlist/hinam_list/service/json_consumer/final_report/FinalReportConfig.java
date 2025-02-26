package com.hinamlist.hinam_list.service.json_consumer.final_report;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FinalReportConfig {
    @Bean
    @Qualifier("${rabbitmq.final-report-collector.exchange}")
    DirectExchange FinalReportCollectorExchange(@Value("${rabbitmq.final-report-collector.exchange}")  String collectorExchangeName) {
        return new DirectExchange(collectorExchangeName);
    }

    @Bean
    @Qualifier("${rabbitmq.final-report-collector.queue}")
    Queue FinalReportCollectorQueue(@Value("${rabbitmq.final-report-collector.queue}")  String collectorQueueName) {
        return new Queue(collectorQueueName);
    }

    @Bean
    public Binding FinalReportCollectorBinding(@Qualifier("${rabbitmq.final-report-collector.exchange}")DirectExchange collectorExchange,
                                               @Qualifier("${rabbitmq.final-report-collector.queue}")Queue collectorQueue) {
        return BindingBuilder.bind(collectorQueue).to(collectorExchange).with("");
    }

}
