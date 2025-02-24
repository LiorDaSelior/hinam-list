package com.hinamlist.hinam_list.service.data_scraping.json_consumer.base;

import com.hinamlist.hinam_list.service.data_scraping.common.RamiLeviFetcher;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;


public abstract class RamiLeviJsonConsumer extends JsonConsumer{

    public RamiLeviJsonConsumer(String queueName, String exchangeName, RabbitAdmin admin, RamiLeviFetcher fetcher, int storeId) {
        super(queueName, exchangeName, admin, fetcher, storeId);
    }
}
