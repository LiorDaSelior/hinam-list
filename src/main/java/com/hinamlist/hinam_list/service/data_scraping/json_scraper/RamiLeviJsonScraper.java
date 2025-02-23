package com.hinamlist.hinam_list.service.data_scraping.json_scraper;

import com.hinamlist.hinam_list.service.data_scraping.json_scraper.exception.APIResponseException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RamiLeviJsonScraper extends AbstractJsonScraper {
    @Override
    public List<Integer> getCategoryIdList() throws IOException, APIResponseException, InterruptedException {
        String uriString = "https://www-api.rami-levy.co.il/api/v2/site/static/menu";
        HttpRequest request = createHttpPostRequest(uriString, new HashMap<>());
        List<Integer> list = new ArrayList<>();
        for (String key : new JSONObject(getResponse(request)).getJSONObject("groups").keySet()) {
            list.add(Integer.valueOf(key));
        }
        return list;
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
            String uriString = "https://www.rami-levy.co.il/api/catalog?";
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
