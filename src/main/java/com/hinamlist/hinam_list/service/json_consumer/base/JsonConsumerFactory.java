package com.hinamlist.hinam_list.service.json_consumer.base;

import com.hinamlist.hinam_list.service.common.json_fetcher.CarrfourFetcher;
import com.hinamlist.hinam_list.service.common.json_fetcher.HaziHinamFetcher;
import com.hinamlist.hinam_list.service.common.json_fetcher.IFetcher;
import com.hinamlist.hinam_list.service.common.json_fetcher.RamiLeviFetcher;
import com.hinamlist.hinam_list.service.common.producer.IProducer;
import org.apache.catalina.Store;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JsonConsumerFactory {
    RabbitAdmin admin;
    Map<String, StoreBean> beanMap;

    @Autowired
    JsonConsumerFactory(RabbitAdmin admin, Map<String, StoreBean> beanMap) {
        this.admin = admin;
        this.beanMap = beanMap;
    }

    public JsonConsumer createJsonConsumer(String storeName, IProducer producer) {
        StoreBean storeBean = beanMap.get(storeName);
        return new JsonConsumer(storeBean.getExchange(),
                admin,
                storeBean.getFetcher(),
                storeBean.getStoreNumber(),
                producer
        );
    }
}
