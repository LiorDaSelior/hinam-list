package com.hinamlist.hinam_list.service.data_scraping.common;

import org.json.JSONObject;

import java.util.Objects;

public interface IFetcher {
    abstract int extractIdFromJsonObject(JSONObject jsonObject);
    abstract String extractBarcodeFromJsonObject(JSONObject jsonObject);
    abstract String extractNameFromJsonObject(JSONObject jsonObject);
    abstract boolean extractIsInKgFromJsonObject(JSONObject jsonObject);
}
