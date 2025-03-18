package com.hinamlist.hinam_list.common;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = {"com.hinamlist.hinam_list.service.json_producer",
        "com.hinamlist.hinam_list.service.json_scraper",
        "com.hinamlist.hinam_list.config"
}
)
public class ProducerLevelPropertiesConfiguration {
}
