package com.hinamlist.hinam_list.service.data_scraping.json_producer;

import com.hinamlist.hinam_list.service.data_scraping.json_scraper.IJsonScraper;
import com.hinamlist.hinam_list.service.data_scraping.json_scraper.exception.APIResponseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public abstract class JsonProducer implements IJsonProducer {
    protected IJsonScraper scraper;
    protected RabbitTemplate rabbitTemplate;
    protected String jsonSenderExchangeName;

    public JsonProducer(RabbitTemplate rabbitTemplate, IJsonScraper scraper, String jsonSenderExchangeName) {
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

    @Async
    @EventListener(ApplicationReadyEvent.class)
    public void sendJSONData() throws IOException, InterruptedException, APIResponseException {
        for (String categoryId : scraper.getCategoryIdList()) {
            JSONArray jsonArray = scraper.getCategoryProductInfo(categoryId);
            for (int i = 0; i < jsonArray.length(); i++) {
                sendJSONObject(jsonArray.getJSONObject(i));
            }
        }
    }

    @Override
    public void sendJSONObject(JSONObject jsonObject) {
        rabbitTemplate.convertAndSend(jsonSenderExchangeName, "", jsonObject.toString());
    }
}
