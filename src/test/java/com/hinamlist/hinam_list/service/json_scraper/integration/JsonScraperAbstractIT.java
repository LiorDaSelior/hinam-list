package com.hinamlist.hinam_list.service.json_scraper.integration;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.hinamlist.hinam_list.service.json_scraper.AbstractJsonScraper;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public abstract class JsonScraperAbstractIT<T extends AbstractJsonScraper> {
    protected T scraper;
    protected MockMvc mockMvc;

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

/*    @DynamicPropertySource
    static void configureProperties (DynamicPropertyRegistry registry) {
        registry.add(baseApiUrlString, wireMockServer::baseUrl);
    }*/


    @Autowired
    public JsonScraperAbstractIT(T scraper, MockMvc mockMvc) {
        this.scraper = scraper;
        this.mockMvc = mockMvc;
    }




}
