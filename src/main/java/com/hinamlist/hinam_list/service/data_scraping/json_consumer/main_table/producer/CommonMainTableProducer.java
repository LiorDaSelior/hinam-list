package com.hinamlist.hinam_list.service.data_scraping.json_consumer.main_table.producer;

import com.hinamlist.hinam_list.service.data_scraping.common.IFetcher;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CommonMainTableProducer {
    public CommonMainTableProducer() {
    }
    public void handleMessage(IFetcher fetcher, String message, int superId) {
        JSONObject json = new JSONObject(message);
        System.out.println("Received message by [" + superId + "] - ID=" + fetcher.extractIdFromJsonObject(json) +
                ", BARKOD=" + fetcher.extractBarcodeFromJsonObject(json));
    }
}
