package com.hinamlist.hinam_list.service.data_scraping.json_consumer.main_table.producer;

import com.hinamlist.hinam_list.service.data_scraping.common.json_fetcher.IFetcher;
import com.hinamlist.hinam_list.service.data_scraping.common.producer.IProducer;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CommonMainTableProducer implements IProducer {
    public CommonMainTableProducer() {
    }
    public void handleMessage(IFetcher fetcher, String message, int storeId, int producerId) {
        JSONObject json = new JSONObject(message);
        System.out.println(String.format("Received by [%d] from Store %d - ID=", producerId, storeId) + fetcher.extractIdFromJsonObject(json) +
                ", BARKOD=" + fetcher.extractBarcodeFromJsonObject(json));
    }
}
