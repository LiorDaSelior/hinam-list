package com.hinamlist.hinam_list.service.json_consumer.base;

import com.hinamlist.hinam_list.service.common.json_fetcher.CarrfourFetcher;
import com.hinamlist.hinam_list.service.common.json_fetcher.HaziHinamFetcher;
import com.hinamlist.hinam_list.service.common.json_fetcher.RamiLeviFetcher;
import com.hinamlist.hinam_list.service.json_consumer.final_report.producer.CommonFinalReportProducer;
import org.apache.catalina.Store;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreBeanConfig {

    // Edit here to add new store options for consumers

    @Bean
    public StoreBean Carrfour(
            @Qualifier("CarrfourExchange") FanoutExchange exchange,
            CarrfourFetcher fetcher,
            @Value("${store.id.carrfour}") int storeId
    ) {
        return new StoreBean(exchange, fetcher, storeId);
    }
    @Bean
    public StoreBean HaziHinam(
            @Qualifier("HaziHinamExchange") FanoutExchange exchange,
            HaziHinamFetcher fetcher,
            @Value("${store.id.hazihinam}") int storeId
    ) {
        return new StoreBean(exchange, fetcher, storeId);
    }
    @Bean
    public StoreBean RamiLevi(
            @Qualifier("RamiLeviExchange") FanoutExchange exchange,
            RamiLeviFetcher fetcher,
            @Value("${store.id.ramilevi}") int storeId
    ) {
        return new StoreBean(exchange, fetcher, storeId);
    }

}
