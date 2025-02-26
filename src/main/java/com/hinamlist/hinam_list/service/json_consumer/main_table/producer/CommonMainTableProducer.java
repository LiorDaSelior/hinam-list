package com.hinamlist.hinam_list.service.json_consumer.main_table.producer;

import com.hinamlist.hinam_list.service.common.json_fetcher.IFetcher;
import com.hinamlist.hinam_list.service.common.producer.IProducer;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommonMainTableProducer implements IProducer {
    protected RabbitTemplate rabbitTemplate;
    protected String collectorExchangeName;

    public CommonMainTableProducer(RabbitTemplate rabbitTemplate,
                                   @Value("${rabbitmq.main-table-collector.exchange}") String collectorExchangeName) {
        this.rabbitTemplate = rabbitTemplate;
        this.collectorExchangeName = collectorExchangeName;
    }

    public void handleMessage(IFetcher fetcher, String message, int storeId, int producerId) {
        JSONObject json = new JSONObject(message);

        if (json.isEmpty()) {
            return;
        }

        int id = fetcher.extractIdFromJsonObject(json);
        String barcode = fetcher.extractBarcodeFromJsonObject(json);

        var newJson = new JSONObject();
        newJson.put("id", id);
        newJson.put("barcode", barcode);
        newJson.put("storeId", storeId);

        rabbitTemplate.convertAndSend(collectorExchangeName, "", newJson.toString());
    }


}
