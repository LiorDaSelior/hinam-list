package com.hinamlist.hinam_list.service.json_scraper;

import com.hinamlist.hinam_list.config.StoreDataConfigProperties;
import com.hinamlist.hinam_list.service.json_scraper.exception.APIResponseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarrfourJsonScraper extends AbstractJsonScraper {
    public CarrfourJsonScraper(StoreDataConfigProperties storeDataConfigProperties) {
        super(storeDataConfigProperties);
    }

    @Override
    public List<String> getCategoryIdList() throws IOException, APIResponseException, InterruptedException {
        //System.out.println(storeDataConfigProperties.getStoreDataMap().get(storeName).targetBaseUrl());
        //System.out.println(storeDataConfigProperties.getStoreDataMap());
        String uriString = storeDataConfigProperties.getStoreDataMap().get(storeName).targetBaseUrl()
                + "/specials/filters?appId=4&sort=%7B%22priority%22:%22desc%22%7D&sort=%7B%22sort%22:%22asc%22%7D&sort=%7B%22id%22:%22desc%22%7D";
        HttpRequest request = createHttpGetRequest(uriString);
        JSONArray jsonArray = new JSONObject(getResponse(request)).getJSONArray("categories");
        List<String> idArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject currentCategoryJson = jsonArray.getJSONObject(i);
            idArray.add(String.valueOf(currentCategoryJson.getInt("id")));
        }
        return idArray;
    }

    @Override
    public JSONArray getCategoryProductInfo(String categoryId) throws IOException, APIResponseException, InterruptedException {
        int requestSize = 500;
        int currentAmount = 0;
        JSONArray currentArray = new JSONArray();
        JSONArray responseArray;
        do {
            String uriString = storeDataConfigProperties.getStoreDataMap().get(storeName).targetBaseUrl() +
                    "/categories/" +
                    categoryId +
                    "/products?from=" +
                    currentAmount +
                    "&languageId=1&minScore=0&size=" +
                    requestSize;
            HttpRequest request = createHttpGetRequest(uriString);
            responseArray = new JSONObject(getResponse(request)).getJSONArray("products");
            currentArray.putAll(responseArray);
            currentAmount += requestSize;
        }
        while (!responseArray.isEmpty());

        return currentArray;
    }
}
