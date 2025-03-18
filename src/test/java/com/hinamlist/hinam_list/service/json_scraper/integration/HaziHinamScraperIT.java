package com.hinamlist.hinam_list.service.json_scraper.integration;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.hinamlist.hinam_list.common.ProducerLevelPropertiesConfiguration;
import com.hinamlist.hinam_list.service.json_scraper.CarrfourJsonScraper;
import com.hinamlist.hinam_list.service.json_scraper.HaziHinamJsonScraper;
import com.hinamlist.hinam_list.service.json_scraper.exception.APIResponseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(classes= ProducerLevelPropertiesConfiguration.class)
@TestPropertySource("classpath:json-scraper-test.properties")
public class HaziHinamScraperIT {
    @Autowired
    protected HaziHinamJsonScraper scraper;

    public HaziHinamScraperIT() {
        addInitStub();
    }

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();


    @DynamicPropertySource
    static void configureProperties (DynamicPropertyRegistry registry) {
        registry.add("store.storeDataMap.HaziHinam.targetBaseUrl", wireMockServer::baseUrl);
    }

    protected void addInitStub() {
        wireMockServer.stubFor(WireMock.get(urlPathEqualTo("/init"))
                .willReturn(
                        ok()));
    }

    @Test
    void getCategoryIdListTest () throws IOException, InterruptedException, APIResponseException {
        //addInitStub();
        //System.out.println(wireMockServer.baseUrl());
        wireMockServer.stubFor(WireMock.get(urlPathEqualTo("/api/Catalog/get"))
                .willReturn(
                ok(
                        """
                                        {
                                            "Results" : {
                                                 "Categories": [
                                                    {
                                                        "SubCategories": [
                                                        {"Id" : 0},
                                                        {"Id" : 1},
                                                        {"Id" : 2}
                                                        ]
                                                    }
                                                ]
                                            }
                                        }
                                        """
                )));

        var result = this.scraper.getCategoryIdList();
        assertEquals(Arrays.asList("0", "1", "2"), result);
    }
}
