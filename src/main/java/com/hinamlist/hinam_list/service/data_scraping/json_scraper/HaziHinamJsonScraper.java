package com.hinamlist.hinam_list.service.data_scraping.json_scraper;

import com.hinamlist.hinam_list.service.data_scraping.json_scraper.exception.APIResponseException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

public class HaziHinamJsonScraper extends AbstractJsonScraper{

    public HaziHinamJsonScraper() throws IOException, InterruptedException, APIResponseException {
        super();
        String uriString = "https://shop.hazi-hinam.co.il/proxy/init";
        HttpRequest request = createHttpGetRequest(uriString);
        var response = getResponse(request);

    }
    @Override
    public List<Integer> getCategoryIdList() throws IOException, APIResponseException, InterruptedException {
        String uriString = "https://shop.hazi-hinam.co.il/proxy/api/Catalog/get";
        HttpRequest request = createHttpGetRequest(uriString);
        JSONArray jsonArray = new JSONObject(getResponse(request)).getJSONObject("Results").getJSONArray("Categories");
        List<Integer> idArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONArray currentSubCategoryJsonArray = jsonArray.getJSONObject(i).getJSONArray("SubCategories");
            for (int j = 0; j < currentSubCategoryJsonArray.length(); j++) {
                idArray.add(currentSubCategoryJsonArray.getJSONObject(j).getInt("Id"));
            }
        }
        return idArray;
    }

    @Override
    public JSONArray getCategoryProductInfo(String categoryId) throws IOException, APIResponseException, InterruptedException {
        JSONArray currentArray = new JSONArray();
        JSONArray responseArray;
        String uriString = "https://shop.hazi-hinam.co.il/proxy/api/item/getItemsBySubCategory?Id=" + categoryId;
        HttpRequest request = createHttpGetRequest(uriString);
        responseArray = new JSONObject(getResponse(request)).getJSONObject("Results").getJSONObject("Category").getJSONObject("SubCategory").getJSONArray("Items");
        currentArray.putAll(responseArray);
        return currentArray;
    }
}
