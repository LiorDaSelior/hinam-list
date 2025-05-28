package com.hinamlist.hinam_list.service.json_producer;

import com.hinamlist.hinam_list.config.StoreDataConfigProperties;
import com.hinamlist.hinam_list.service.json_scraper.AbstractJsonScraper;
import com.hinamlist.hinam_list.service.json_scraper.exception.APIResponseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

import static com.hinamlist.hinam_list.service.json_producer.ProducerMessageTypeEnum.DATA;
import static com.hinamlist.hinam_list.service.json_producer.ProducerMessageTypeEnum.FINISHED;

public class JsonProducer {
    public final static String SUFFIX = "Producer";

    protected AbstractJsonScraper scraper;
    protected RabbitTemplate rabbitTemplate;

    protected String storeName;
    protected  StoreDataConfigProperties storeDataConfigProperties;

    @Value("${rabbitmq.main-json-producer.exchange.name}")
    protected String jsonSenderExchangeName;
    protected String topicName;

    public JsonProducer(RabbitTemplate rabbitTemplate, AbstractJsonScraper scraper,
                        String storeName, StoreDataConfigProperties storeDataConfigProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.scraper = scraper;
        this.storeName = storeName;
        this.storeDataConfigProperties = storeDataConfigProperties;
        this.topicName = storeDataConfigProperties.getStoreDataMap().get(storeName).mainTopicName();
    }

    public void sendJSONDataByCategory(String categoryId) throws IOException, InterruptedException, APIResponseException {
        JSONArray jsonArray = scraper.getCategoryProductInfo(categoryId);
        for (int i = 0; i < jsonArray.length(); i++) {
            sendJSONObject(jsonArray.getJSONObject(i));
        }
    }

    @Async
    public void sendJSONData() throws IOException, InterruptedException, APIResponseException {
        for (String categoryId : scraper.getCategoryIdList()) {
            JSONArray jsonArray = scraper.getCategoryProductInfo(categoryId);
            for (int i = 0; i < jsonArray.length(); i++) {
                sendJSONObject(jsonArray.getJSONObject(i));
            }
        }
        sendFinishedStatus();
    }

    public void sendJSONObject(JSONObject jsonObject) {
        MessageProperties msgProperties = new MessageProperties();
        msgProperties.setHeader("messageType", DATA);
        msgProperties.setHeader("store", storeName);
        Message message = new Message(jsonObject.toString().getBytes(), msgProperties);
        rabbitTemplate.convertAndSend(jsonSenderExchangeName, this.topicName, message);
    }

    public void sendFinishedStatus() {
        MessageProperties msgProperties = new MessageProperties();
        msgProperties.setHeader("messageType", FINISHED);
        msgProperties.setHeader("store", storeName);
        Message message = new Message("".getBytes(), msgProperties);
        rabbitTemplate.convertAndSend(jsonSenderExchangeName, this.topicName, message);
    }
}
