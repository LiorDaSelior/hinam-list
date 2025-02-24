package com.hinamlist.hinam_list.service.data_scraping.json_consumer.base;

import com.hinamlist.hinam_list.service.data_scraping.common.CarrfourFetcher;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;

public abstract class CarrfourJsonConsumer extends JsonConsumer{

    public CarrfourJsonConsumer(String queueName, String exchangeName, RabbitAdmin admin, CarrfourFetcher fetcher, int storeId) {
        super(queueName, exchangeName, admin, fetcher, storeId);
    }
}
