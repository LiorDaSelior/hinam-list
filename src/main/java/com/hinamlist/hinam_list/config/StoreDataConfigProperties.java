package com.hinamlist.hinam_list.config;

import com.hinamlist.hinam_list.service.json_producer.JsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "store")
public class StoreDataConfigProperties {
    private Map<String, StoreData> storeDataMap;
    private final Set<String> requiredStoreNameSet;

    @Autowired
    public StoreDataConfigProperties(Map<String, JsonProducer> producerMap) {
        requiredStoreNameSet = producerMap.keySet().stream()
                .map(producerName -> producerName.replaceFirst("Producer$", ""))
                .collect(Collectors.toSet());
    }

    public Map<String, StoreData> getStoreDataMap() {
        return storeDataMap;
    }

    public void setStoreDataMap(Map<String, StoreData> storeDataMap) {
        this.storeDataMap = storeDataMap;
    }

    public Set<String> getRequiredStoreNameSet() {
        return requiredStoreNameSet;
    }
    /*    @PostConstruct
    private void validate() {
        validateStoresMatchProducerBeans();
        validateStoresData();
    }

    private void validateStoresMatchProducerBeans() {
        Set<String> missingStoresSet = new HashSet<>(requiredStoreNameSet);
        missingStoresSet.removeAll(storeDataMap.keySet());
        if (!missingStoresSet.isEmpty()) {
            throw new RuntimeException("Missing stores in configuration: " + missingStoresSet);
        }
    }
    private void validateStoresData() {
        Set<Integer> usedStoreId = new HashSet<>();
        for (var entry : storeDataMap.entrySet()) {
            int storeId = entry.getValue().databaseId();

            if (usedStoreId.contains(storeId))
                throw new RuntimeException("Same database ID for multiple stores in not allowed");
            usedStoreId.add(storeId);

            if (entry.getValue().exchangeName() == null)
                throw new RuntimeException("Exchange name is not set for store " + entry.getKey());
        }
    }*/
}
