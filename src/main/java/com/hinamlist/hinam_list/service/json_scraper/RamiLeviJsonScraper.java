package com.hinamlist.hinam_list.service.json_scraper;

import com.hinamlist.hinam_list.config.StoreDataConfigProperties;
import com.hinamlist.hinam_list.service.json_scraper.exception.APIResponseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RamiLeviJsonScraper extends AbstractJsonScraper {
    public RamiLeviJsonScraper(StoreDataConfigProperties storeDataConfigProperties) {
        super(storeDataConfigProperties);
    }

    @Override
    public List<String> getCategoryIdList() throws IOException, APIResponseException, InterruptedException {
        String uriString = storeDataConfigProperties.getStoreDataMap().get(storeName).targetBaseUrl() +
                "/v2/site/static/menu";
        HttpRequest request = createHttpPostRequest(uriString, new HashMap<>());
        return new ArrayList<>(new JSONObject(getResponse(request)).getJSONObject("groups").keySet());
    }

    @Override
    public JSONArray getCategoryProductInfo(String categoryId) throws IOException, InterruptedException, APIResponseException {
        int requestSize = 30;
        int currentAmount = 0;
        long sleepTimer = 1;
        boolean breakCheck;

        JSONArray currentArray = new JSONArray();
        JSONArray responseArray = new JSONArray();
        Map<String, String> propertyMap = new HashMap<>();
        do {
            breakCheck = false;
            String uriString = storeDataConfigProperties.getStoreDataMap().get(storeName).targetBaseUrl() +
                    "/catalog?";
            propertyMap.clear();
            propertyMap.put("g", categoryId);
            propertyMap.put("from", String.valueOf(currentAmount));
            HttpRequest request = createHttpPostRequest(uriString, propertyMap);

            responseArray.clear();
            try {
                responseArray = new JSONObject(getResponse(request)).getJSONArray("data");
                currentArray.putAll(responseArray);
                sleepTimer = 1;
                currentAmount += requestSize;
                if (responseArray.isEmpty()) {
                    breakCheck = true;
                }
            }
            catch (APIResponseException e) {
                if (e.getStatusCode() == 429) {
                    try {
                        Thread.sleep(sleepTimer * 100);
                    } catch (InterruptedException _) {}
                    sleepTimer *= 2;
                }
                else
                    throw e;
            }
        }
        while (!breakCheck);

        return currentArray;
    }
}
