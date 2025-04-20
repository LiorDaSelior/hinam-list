package com.hinamlist.hinam_list.service.json_producer;

import com.hinamlist.hinam_list.service.json_scraper.AbstractJsonScraper;
import com.hinamlist.hinam_list.service.json_scraper.exception.APIResponseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;

public class JsonProducer {
    public final static String SUFFIX = "Producer";

    protected AbstractJsonScraper scraper;
    protected RabbitTemplate rabbitTemplate;
    protected String jsonSenderExchangeName;

    public JsonProducer(RabbitTemplate rabbitTemplate, AbstractJsonScraper scraper, String jsonSenderExchangeName) {
        this.rabbitTemplate = rabbitTemplate;
        this.scraper = scraper;
        this.jsonSenderExchangeName = jsonSenderExchangeName;
    }

    public void sendJSONDataByCategory(String categoryId) throws IOException, InterruptedException, APIResponseException {
        JSONArray jsonArray = scraper.getCategoryProductInfo(categoryId);
        for (int i = 0; i < jsonArray.length(); i++) {
            sendJSONObject(jsonArray.getJSONObject(i));
        }
    }

    public void sendJSONData() throws IOException, InterruptedException, APIResponseException {
        for (String categoryId : scraper.getCategoryIdList()) {
            JSONArray jsonArray = scraper.getCategoryProductInfo(categoryId);
            for (int i = 0; i < jsonArray.length(); i++) {
                sendJSONObject(jsonArray.getJSONObject(i));
            }
        }
        sendJSONObject(new JSONObject());
    }

    public void sendJSONObject(JSONObject jsonObject) {
        rabbitTemplate.convertAndSend(jsonSenderExchangeName, "", jsonObject.toString());
    }
}
