package com.hinamlist.hinam_list.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

//@ActiveProfiles("test")
@SpringBootTest(classes= PropertiesITConfiguration.class)
@TestPropertySource("classpath:application.properties")
public class StoreDataConfigPropertiesIT {
    private final StoreDataConfigProperties configProperties;

    @Autowired
    public StoreDataConfigPropertiesIT(StoreDataConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    @Test
    public void validateStoresMatchProducerBeans() {
        if (configProperties.getRequiredStoreNameSet().isEmpty()) {
            assertNull(configProperties.getStoreDataMap());
        }
        else {
            assertEquals(
                    configProperties.getRequiredStoreNameSet(),
                    configProperties.getStoreDataMap().keySet()
            );
        }
    }

    @Test
    public void validateStoresData() {
        assertNotNull(configProperties.getStoreDataMap());
        Set<Integer> usedStoreId = new HashSet<>();
        for (var entry : configProperties.getStoreDataMap().entrySet()) {
            int storeId = entry.getValue().databaseId();

            assertFalse(usedStoreId.contains(storeId), entry.getKey() + " has a similar database id to another store");
            usedStoreId.add(storeId);

            assertNotNull(entry.getValue().exchangeName(), entry.getKey() + " is missing exchange name in properties");
        }
    }
}
