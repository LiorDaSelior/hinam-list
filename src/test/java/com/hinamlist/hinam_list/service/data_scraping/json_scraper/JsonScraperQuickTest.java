package com.hinamlist.hinam_list.service.data_scraping.json_scraper;

import com.hinamlist.hinam_list.service.data_scraping.json_scraper.exception.APIResponseException;
import org.json.JSONArray;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class JsonScraperQuickTest<T extends IJsonScraper> {

    protected T instance;

    abstract protected T createInstance() throws IOException, InterruptedException, APIResponseException;
    abstract protected List<String> getCategoryIdTestList();

    @BeforeAll
    protected void init() throws IOException, InterruptedException, APIResponseException {
        instance = createInstance();
    }

    @Test
    @Order(1)
    public void getCategoryIdListTest() {
        List<String> tempList = null;
        try {
            tempList = instance.getCategoryIdList();
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertNotNull(tempList);
        assertNotEquals(0, tempList.size());
    }

    @ParameterizedTest
    @Order(2)
    @MethodSource("getCategoryIdTestList")
    public void getCategoryProductInfo(String categoryId) {
        JSONArray tempArray = null;
        try {
            tempArray = instance.getCategoryProductInfo(categoryId);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertNotNull(tempArray);
        assertNotEquals(0, tempArray.length());
    }




}
