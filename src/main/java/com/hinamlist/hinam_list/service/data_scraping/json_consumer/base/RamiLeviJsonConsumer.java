package com.hinamlist.hinam_list.service.data_scraping.json_consumer.base;

import com.hinamlist.hinam_list.service.data_scraping.common.json_fetcher.RamiLeviFetcher;
import com.hinamlist.hinam_list.service.data_scraping.common.producer.IProducer;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;


public class RamiLeviJsonConsumer extends JsonConsumer{
    public RamiLeviJsonConsumer(FanoutExchange exchange, RabbitAdmin admin, RamiLeviFetcher fetcher, int storeId, IProducer producer) {
        super(exchange, admin, fetcher, storeId, producer);
    }
}