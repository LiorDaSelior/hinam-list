package com.hinamlist.hinam_list.service.json_consumer.final_report.collector;

import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "${rabbitmq.final-report-collector.queue}")
public class FinalReportCollector {

    private final int storeAmount;
    private final AtomicInteger counter = new AtomicInteger(0);

    FinalReportCollector(@Value("${store.amount}") int storeAmount) {
        this.storeAmount = storeAmount;
    }

    @RabbitHandler
    public void receive(String recordJson) {
        JSONObject recordJsonObject = new JSONObject(recordJson);
        int storeId = recordJsonObject.getInt("storeId");
        System.out.printf("Store %d finished scraping.\n", storeId);
        if (counter.incrementAndGet() == storeAmount) {
            System.out.println("All stores finished scraping!");
        }
    }
}
