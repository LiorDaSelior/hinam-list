package com.hinamlist.hinam_list.service.json_scraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hinamlist.hinam_list.config.StoreDataConfigProperties;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HaziHinamScraperTest {
    @Mock
    StoreDataConfigProperties properties;

    @InjectMocks
    HaziHinamJsonScraper scraper;

    String jsonResponseString = """
                                        {
                                            "Results" : {
                                                 "Categories": [
                                                    {
                                                        "Id" : 1000,
                                                        "SubCategories": [
                                                        {"Id" : 0},
                                                        {"Id" : 1}
                                                        ]
                                                    },
                                                    {
                                                        "Id" : 1001,
                                                        "SubCategories": [
                                                        {"Id" : 2},
                                                        {"Id" : 3}
                                                        ]
                                                    }
                                                ]
                                            }
                                        }
                                """;

    @Test
    @Order(1)
    @DisplayName("Category Extraction")
    void extractJsonCategoryArrayFromJsonResponseStringTest() throws JsonProcessingException {
        JSONObject expectedResponseObject = new JSONObject(jsonResponseString);

        ObjectMapper mapper = new ObjectMapper();

        assertEquals(mapper.readTree(expectedResponseObject.getJSONObject("Results").getJSONArray("Categories").toString()),
                mapper.readTree(scraper.extractJsonCategoryArrayFromJsonResponseString(jsonResponseString).toString()));
    }

    @Test
    @Order(2)
    @DisplayName("Subcategory Extraction")
    void extractSubcategoryIdListFromCategoryJsonArrayTest() {
        JSONObject expectedResponseObject = new JSONObject(jsonResponseString);
        JSONArray expectedCategoryJsonArray = expectedResponseObject.getJSONObject("Results").getJSONArray("Categories");
        assertEquals(Arrays.asList("0","1","2","3"),
                scraper.extractSubcategoryIdListFromCategoryJsonArray(expectedCategoryJsonArray));
    }
}
