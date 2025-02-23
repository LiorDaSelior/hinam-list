package com.hinamlist.hinam_list.service.data_scraping.fetcher;

import com.hinamlist.hinam_list.service.data_scraping.json_scraper.IJsonScraper;
import com.hinamlist.hinam_list.service.data_scraping.json_scraper.exception.APIResponseException;
import com.hinamlist.hinam_list.service.data_scraping.product.Product;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFetcher<T extends IJsonScraper> implements IFetcher {
    protected final T scraper;
    protected AbstractFetcher(T scraper) {
        this.scraper = scraper;
    }

    // Abstract Json Extractors
    abstract protected int extractIdFromJsonObject(JSONObject jsonObject);
    abstract protected String extractBarcodeFromJsonObject(JSONObject jsonObject);
    abstract protected String extractNameFromJsonObject(JSONObject jsonObject);
    abstract protected boolean extractIsInKgFromJsonObject(JSONObject jsonObject);

    // KeyArray Functions
    private JSONObject getRelevantObjectByKeyArray(JSONObject jsonObject , String[] keyArray) {
        JSONObject currentJsonObject = jsonObject;
        for (int i = 0; i < keyArray.length - 1; i++) {
            currentJsonObject = currentJsonObject.getJSONObject(keyArray[i]);
        }
        return currentJsonObject;
    }
    protected String getStringByKeyArray(JSONObject jsonObject , String[] keyArray) {
        return getRelevantObjectByKeyArray(jsonObject, keyArray).getString(keyArray[keyArray.length - 1]);
    }
    protected int getIntByKeyArray(JSONObject jsonObject , String[] keyArray) {
        return getRelevantObjectByKeyArray(jsonObject, keyArray).getInt(keyArray[keyArray.length - 1]);
    }

    // Main Product Functions

    protected Product createProductFromJsonObject(JSONObject jsonObject, int categoryId) {
        return new Product(
                extractIdFromJsonObject(jsonObject),
                extractBarcodeFromJsonObject(jsonObject),
                categoryId,
                extractNameFromJsonObject(jsonObject),
                extractIsInKgFromJsonObject(jsonObject)
        );
    }
    public List<Product> getCategoryProductList(int categoryId) throws IOException, APIResponseException, InterruptedException {
        List<Product> productList = new ArrayList<>();
        JSONArray jsonArray = scraper.getCategoryProductInfo(String.valueOf(categoryId)); // TODO: consider change categoryId list to list<string>
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject currentProductJson = jsonArray.getJSONObject(i);

            productList.add(
                    createProductFromJsonObject(currentProductJson, categoryId)
            );

        }
        return  productList;
    }
    public List<Product> getProductList() throws IOException, APIResponseException, InterruptedException {
        List<Product> productList = new ArrayList<>();
        for (int categoryId : scraper.getCategoryIdList()) {
            productList.addAll(getCategoryProductList(categoryId));
        }
        return productList;
    }
}
