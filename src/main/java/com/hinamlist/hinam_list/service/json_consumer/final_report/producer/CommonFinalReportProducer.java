package com.hinamlist.hinam_list.service.json_consumer.final_report.producer;

import com.hinamlist.hinam_list.service.common.json_fetcher.IFetcher;
import com.hinamlist.hinam_list.service.common.producer.IProducer;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommonFinalReportProducer implements IProducer {
    protected RabbitTemplate rabbitTemplate;
    protected String collectorExchangeName;

    public CommonFinalReportProducer(RabbitTemplate rabbitTemplate,
                                     @Value("${rabbitmq.final-report-collector.exchange}") String collectorExchangeName) {
        this.rabbitTemplate = rabbitTemplate;
        this.collectorExchangeName = collectorExchangeName;
    }

    public void handleMessage(IFetcher fetcher, String message, int storeId, int producerId) {
        JSONObject json = new JSONObject(message);
        if (json.isEmpty()) {
            var newJson = new JSONObject();
            newJson.put("isFinished", true);
            newJson.put("storeId", storeId);
            rabbitTemplate.convertAndSend(collectorExchangeName, "", newJson.toString());
        }
    }
}
