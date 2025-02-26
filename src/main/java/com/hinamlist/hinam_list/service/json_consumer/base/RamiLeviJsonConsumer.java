package com.hinamlist.hinam_list.service.json_consumer.base;

import com.hinamlist.hinam_list.service.common.json_fetcher.RamiLeviFetcher;
import com.hinamlist.hinam_list.service.common.producer.IProducer;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;


public class RamiLeviJsonConsumer extends JsonConsumer{
    public RamiLeviJsonConsumer(FanoutExchange exchange, RabbitAdmin admin, RamiLeviFetcher fetcher, int storeId, IProducer producer) {
        super(exchange, admin, fetcher, storeId, producer);
    }
}