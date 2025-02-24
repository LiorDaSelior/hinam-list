package com.hinamlist.hinam_list.service.data_scraping.json_consumer.main_table.producer;

import com.hinamlist.hinam_list.service.data_scraping.json_consumer.base.HaziHinamJsonConsumer;
import com.hinamlist.hinam_list.service.data_scraping.common.HaziHinamFetcher;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HaziHinamGeneralTableProducer extends HaziHinamJsonConsumer {
    protected CommonMainTableProducer producer;

    @Autowired
    protected HaziHinamGeneralTableProducer(
            @Value("${rabbitmq.json-sender.exchange.hazihinam}") String exchange,
            RabbitAdmin admin,
            HaziHinamFetcher fetcher,
            @Value("${store.id.hazihinam}") int storeId,
            CommonMainTableProducer producer
            ) {
        super("HaziHinamMainTableQueue", exchange, admin, fetcher, storeId);
        this.producer = producer;
    }

    @RabbitListener(queues = "HaziHinamMainTableQueue")
    @Override
    public void receiveMessage(String message) {
        producer.handleMessage(fetcher, message, actual_id);
    }
}
