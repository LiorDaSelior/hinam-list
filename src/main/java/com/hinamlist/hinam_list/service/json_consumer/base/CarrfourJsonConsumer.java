package com.hinamlist.hinam_list.service.json_consumer.base;

import com.hinamlist.hinam_list.service.common.json_fetcher.CarrfourFetcher;
import com.hinamlist.hinam_list.service.common.producer.IProducer;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

public class CarrfourJsonConsumer extends JsonConsumer{

    public CarrfourJsonConsumer(FanoutExchange exchange, RabbitAdmin admin, CarrfourFetcher fetcher, int storeId, IProducer producer) {
        super( exchange, admin, fetcher, storeId, producer);
    }
}
