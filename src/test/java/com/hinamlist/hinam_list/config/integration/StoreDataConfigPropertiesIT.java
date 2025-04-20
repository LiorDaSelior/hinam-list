package com.hinamlist.hinam_list.config.integration;

import com.hinamlist.hinam_list.common.ProducerLevelPropertiesConfiguration;
import com.hinamlist.hinam_list.config.StoreDataConfigProperties;
import com.hinamlist.hinam_list.service.json_producer.JsonProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes= ProducerLevelPropertiesConfiguration.class)
@TestPropertySource("classpath:application.properties")
public class StoreDataConfigPropertiesIT {
    private final StoreDataConfigProperties configProperties;
    private final Map<String, JsonProducer> producerMap;


    @Autowired
    public StoreDataConfigPropertiesIT(
            StoreDataConfigProperties configProperties,
            Map<String, JsonProducer> producerMap
    ) {
        this.configProperties = configProperties;
        this.producerMap = producerMap;
    }


/*    @Test
    public void validateStoresMatchProducerBeans() {
        var requiredStoreNameSet = producerMap.keySet().stream()
                .map(producerName -> StoreDataConfigProperties.getStoreName(producerName, JsonProducer.SUFFIX))
                .collect(Collectors.toSet());
        //System.out.println(requiredStoreNameSet);

        if (requiredStoreNameSet.isEmpty()) {
            assertNull(configProperties.getStoreDataMap());
        }
        else {
            assertEquals(
                    requiredStoreNameSet,
                    configProperties.getStoreDataMap().keySet()
            );
        }
    }*/

    @Test
    public void validateStoresData() {
        assertNotNull(configProperties.getStoreDataMap());
        Set<Integer> usedStoreId = new HashSet<>();
        for (var entry : configProperties.getStoreDataMap().entrySet()) {
            int storeId = entry.getValue().databaseId();

            assertFalse(usedStoreId.contains(storeId), entry.getKey() + " has a similar database id to another store");
            usedStoreId.add(storeId);

            assertNotNull(entry.getValue().targetBaseUrl(), entry.getKey() + " is missing base URL in properties");
            assertNotNull(entry.getValue().messageStoreHeader(), entry.getKey() + " is missing message header in properties");
            assertNotNull(entry.getValue().mainTopicName(), entry.getKey() + " is missing topic name in properties");
        }
    }
}
