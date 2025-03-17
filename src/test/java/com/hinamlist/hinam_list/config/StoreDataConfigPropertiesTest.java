package com.hinamlist.hinam_list.config;

import com.hinamlist.hinam_list.service.json_producer.JsonProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

@Import(PropertiesTestConfiguration.class)
@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = StoreDataConfigProperties.class)
public class StoreDataConfigPropertiesTest {
    private final StoreDataConfigProperties configProperties;

    @Autowired
    public StoreDataConfigPropertiesTest(StoreDataConfigProperties configProperties) {
        this.configProperties = configProperties;
    }


    @Test
    public void testRequiredStoreNameList() {
        Set<String> expectedResultSet = new HashSet<>();
        expectedResultSet.add("HaziHinam");
        expectedResultSet.add("RamiLevi");

        assertEquals(expectedResultSet, configProperties.getRequiredStoreNameSet());
    }


}
