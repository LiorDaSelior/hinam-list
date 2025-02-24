package com.hinamlist.hinam_list.service.data_scraping.json_consumer.main_table.producer;

import com.hinamlist.hinam_list.service.data_scraping.json_consumer.base.RamiLeviJsonConsumer;
import com.hinamlist.hinam_list.service.data_scraping.common.RamiLeviFetcher;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RamiLeviGeneralTableProducer extends RamiLeviJsonConsumer {
    protected CommonMainTableProducer producer;

    @Autowired
    protected RamiLeviGeneralTableProducer(
            @Value("${rabbitmq.json-sender.exchange.ramilevi}") String exchange,
            RabbitAdmin admin,
            RamiLeviFetcher fetcher,
            @Value("${store.id.ramilevi}") int storeId,
            CommonMainTableProducer producer
    ) {
        super("RamiLeviMainTableQueue", exchange, admin, fetcher, storeId);
        this.producer = producer;
    }

    @RabbitListener(queues = "RamiLeviMainTableQueue")
    @Override
    public void receiveMessage(String message) {
        producer.handleMessage(fetcher, message, actual_id);
    }
}
