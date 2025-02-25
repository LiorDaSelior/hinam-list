package com.hinamlist.hinam_list.service.data_scraping.common.json_fetcher;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HaziHinamFetcher extends AbstractFetcher {
    @Override
    public int extractIdFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getInt("Id");
    }

    @Override
    public String extractBarcodeFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getString("BarKod");
    }

    @Override
    public String extractNameFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getString("Name");
    }

    @Override
    public boolean extractIsInKgFromJsonObject(JSONObject jsonObject) {
        return Objects.equals(jsonObject.getString("WeightUnitDesc"), "נמכר במשקל");
    }
}
