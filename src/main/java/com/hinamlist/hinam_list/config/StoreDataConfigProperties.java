package com.hinamlist.hinam_list.config;

import com.hinamlist.hinam_list.service.json_producer.JsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "store")
public class StoreDataConfigProperties {
    private Map<String, StoreData> storeDataMap;

    public Map<String, StoreData> getStoreDataMap() {
        return storeDataMap;
    }

    public void setStoreDataMap(Map<String, StoreData> storeDataMap) {
        this.storeDataMap = storeDataMap;
    }
}
