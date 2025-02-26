package com.hinamlist.hinam_list.service.json_consumer.base;

import com.hinamlist.hinam_list.service.common.json_fetcher.HaziHinamFetcher;
import com.hinamlist.hinam_list.service.common.producer.IProducer;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

public class HaziHinamJsonConsumer extends JsonConsumer{

    public HaziHinamJsonConsumer( FanoutExchange exchange, RabbitAdmin admin, HaziHinamFetcher fetcher, int storeId, IProducer producer) {
        super( exchange, admin, fetcher, storeId, producer);
    }
}