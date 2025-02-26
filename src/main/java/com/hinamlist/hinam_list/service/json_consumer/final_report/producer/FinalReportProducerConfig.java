package com.hinamlist.hinam_list.service.json_consumer.final_report.producer;

import com.hinamlist.hinam_list.service.common.json_fetcher.CarrfourFetcher;
import com.hinamlist.hinam_list.service.common.json_fetcher.HaziHinamFetcher;
import com.hinamlist.hinam_list.service.common.json_fetcher.RamiLeviFetcher;
import com.hinamlist.hinam_list.service.json_consumer.base.CarrfourJsonConsumer;
import com.hinamlist.hinam_list.service.json_consumer.base.HaziHinamJsonConsumer;
import com.hinamlist.hinam_list.service.json_consumer.base.RamiLeviJsonConsumer;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinalReportProducerConfig {

    @Bean
    public CarrfourJsonConsumer CarrfourFinalReportProducer(
            @Qualifier("CarrfourExchange") FanoutExchange exchange,
            RabbitAdmin admin,
            CarrfourFetcher fetcher,
            @Value("${store.id.carrfour}") int storeId,
            CommonFinalReportProducer producer
    ) {
        return  new CarrfourJsonConsumer(exchange, admin, fetcher, storeId, producer);
    }

    @Bean
    public HaziHinamJsonConsumer HaziHinamFinalReportProducer(
            @Qualifier("HaziHinamExchange") FanoutExchange exchange,
            RabbitAdmin admin,
            HaziHinamFetcher fetcher,
            @Value("${store.id.hazihinam}") int storeId,
            CommonFinalReportProducer producer
    ) {
        return  new HaziHinamJsonConsumer(exchange, admin, fetcher, storeId, producer);
    }

    @Bean
    public RamiLeviJsonConsumer RamiLeviFinalReportProducer(
            @Qualifier("RamiLeviExchange") FanoutExchange exchange,
            RabbitAdmin admin,
            RamiLeviFetcher fetcher,
            @Value("${store.id.ramilevi}") int storeId,
            CommonFinalReportProducer producer
    ) {
        return  new RamiLeviJsonConsumer(exchange, admin, fetcher, storeId, producer);
    }
}
