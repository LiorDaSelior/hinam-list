package com.hinamlist.hinam_list.service.json_consumer.main_table.producer;

import com.hinamlist.hinam_list.service.common.json_fetcher.CarrfourFetcher;
import com.hinamlist.hinam_list.service.common.json_fetcher.HaziHinamFetcher;
import com.hinamlist.hinam_list.service.common.json_fetcher.RamiLeviFetcher;
import com.hinamlist.hinam_list.service.json_consumer.base.JsonConsumer;
import com.hinamlist.hinam_list.service.json_consumer.base.JsonConsumerFactory;
import com.hinamlist.hinam_list.service.json_consumer.final_report.producer.CommonFinalReportProducer;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainTableProducerConfig {
    @Bean
    public JsonConsumer CarrfourGeneralTableProducer(
            JsonConsumerFactory factory,
            CommonMainTableProducer producer) {
        return factory.createJsonConsumer("Carrfour", producer);
    }

    @Bean
    public JsonConsumer HaziHinamGeneralTableProducer(
            JsonConsumerFactory factory,
            CommonMainTableProducer producer) {
        return factory.createJsonConsumer("HaziHinam", producer);
    }

    @Bean
    public JsonConsumer RamiLeviGeneralTableProducer(
            JsonConsumerFactory factory,
            CommonMainTableProducer producer) {
        return factory.createJsonConsumer("RamiLevi", producer);
    }
}
