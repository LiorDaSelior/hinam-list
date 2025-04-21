package com.hinamlist.hinam_list.service.common.json_fetcher;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

public class RamiLeviFetcher extends AbstractFetcher {
    @Override
    public int extractIdFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getInt("id");
    }

    @Override
    public String extractBarcodeFromJsonObject(JSONObject jsonObject) {
        return String.valueOf(jsonObject.getLong("barcode"));
    }

    @Override
    public String extractNameFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getString("name");
    }

    @Override
    public boolean extractIsInKgFromJsonObject(JSONObject jsonObject) {
        return (getIntByKeyArray(jsonObject, new String[] {"prop", "by_kilo"}) == 1);
    }
}
