package com.hinamlist.hinam_list.service.data_scraping.common.json_fetcher;

import org.json.JSONObject;

public interface IFetcher {
    abstract int extractIdFromJsonObject(JSONObject jsonObject);
    abstract String extractBarcodeFromJsonObject(JSONObject jsonObject);
    abstract String extractNameFromJsonObject(JSONObject jsonObject);
    abstract boolean extractIsInKgFromJsonObject(JSONObject jsonObject);
}
