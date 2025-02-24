package com.hinamlist.hinam_list.service.data_scraping.json_consumer.base;

import com.hinamlist.hinam_list.service.data_scraping.common.HaziHinamFetcher;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;

public abstract class HaziHinamJsonConsumer extends JsonConsumer{

    public HaziHinamJsonConsumer(String queueName, String exchangeName, RabbitAdmin admin, HaziHinamFetcher fetcher, int storeId) {
        super(queueName, exchangeName, admin, fetcher, storeId);
    }
}
