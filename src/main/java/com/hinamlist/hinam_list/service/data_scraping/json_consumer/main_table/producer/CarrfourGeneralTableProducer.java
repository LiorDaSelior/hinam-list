package com.hinamlist.hinam_list.service.data_scraping.json_consumer.main_table.producer;

import com.hinamlist.hinam_list.service.data_scraping.json_consumer.base.CarrfourJsonConsumer;
import com.hinamlist.hinam_list.service.data_scraping.common.CarrfourFetcher;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CarrfourGeneralTableProducer extends CarrfourJsonConsumer {
    protected CommonMainTableProducer producer;

    @Autowired
    protected CarrfourGeneralTableProducer(
            @Value("${rabbitmq.json-sender.exchange.carrfour}") String exchange,
            RabbitAdmin admin,
            CarrfourFetcher fetcher,
            @Value("${store.id.carrfour}") int storeId,
            CommonMainTableProducer producer
    ) {
        super("CarrfourMainTableQueue", exchange, admin, fetcher, storeId);
        this.producer = producer;
    }

    @RabbitListener(queues = "CarrfourMainTableQueue")
    @Override
    public void receiveMessage(String message) {
        producer.handleMessage(fetcher, message, actual_id);
    }
}
