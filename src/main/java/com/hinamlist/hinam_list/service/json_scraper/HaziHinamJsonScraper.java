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
public class HaziHinamJsonScraper extends AbstractJsonScraper{

    public HaziHinamJsonScraper(StoreDataConfigProperties storeDataConfigProperties) throws IOException, InterruptedException, APIResponseException {
        super(storeDataConfigProperties);
        if (storeDataConfigProperties.getStoreDataMap().containsKey(storeName)) {
            String uriString = storeDataConfigProperties.getStoreDataMap().get(storeName).targetBaseUrl() + "/init";
            HttpRequest request = createHttpGetRequest(uriString);
            getResponse(request);
        }
    }

    @Override
    public List<String> getCategoryIdList() throws IOException, APIResponseException, InterruptedException {
        String uriString = storeDataConfigProperties.getStoreDataMap().get(storeName).targetBaseUrl() +
                "/api/Catalog/get";
        HttpRequest request = createHttpGetRequest(uriString);
        String jsonResponseString = getResponse(request);

        JSONArray categoryJsonArray = extractJsonCategoryArrayFromJsonResponseString(jsonResponseString);

        return extractSubcategoryIdListFromCategoryJsonArray(categoryJsonArray);
    }

    protected JSONArray extractJsonCategoryArrayFromJsonResponseString(String jsonResponse) {
        return new JSONObject(jsonResponse)
                .getJSONObject("Results")
                .getJSONArray("Categories");
    }

    protected List<String> extractSubcategoryIdListFromCategoryJsonArray(JSONArray categoryJsonArray) {
        List<String> resultIdArray = new ArrayList<>();
        for (int i = 0; i < categoryJsonArray.length(); i++) {
            JSONArray currentSubCategoryJsonArray = categoryJsonArray
                    .getJSONObject(i)
                    .getJSONArray("SubCategories");
            for (int j = 0; j < currentSubCategoryJsonArray.length(); j++) {
                resultIdArray.add(
                        String.valueOf(
                                currentSubCategoryJsonArray
                                        .getJSONObject(j)
                                        .getInt("Id")));
            }
        }
        return resultIdArray;
    }


    @Override
    public JSONArray getCategoryProductInfo(String categoryId) throws IOException, APIResponseException, InterruptedException {
        String uriString = storeDataConfigProperties.getStoreDataMap().get(storeName).targetBaseUrl() +
                "/api/item/getItemsBySubCategory?Id=" + categoryId;
        HttpRequest request = createHttpGetRequest(uriString);
        String jsonResponseString = getResponse(request);
        return extractProductArrayFromJsonResponseString(jsonResponseString);
    }

    protected JSONArray extractProductArrayFromJsonResponseString(String jsonResponse) {
        return new JSONObject(jsonResponse)
                .getJSONObject("Results")
                .getJSONObject("Category")
                .getJSONObject("SubCategory")
                .getJSONArray("Items");
    }
}
