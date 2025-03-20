package com.hinamlist.hinam_list.service.json_scraper.integration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@TestConfiguration
@ComponentScan(basePackages = {
        "com.hinamlist.hinam_list.service.json_scraper",
        "com.hinamlist.hinam_list.config"
}
)
public class JsonScraperITConfiguration {
}
