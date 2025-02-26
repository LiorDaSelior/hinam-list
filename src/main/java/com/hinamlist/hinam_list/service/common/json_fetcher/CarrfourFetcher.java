package com.hinamlist.hinam_list.service.common.json_fetcher;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CarrfourFetcher extends AbstractFetcher{
    @Override
    public int extractIdFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getInt("productId");
    }

    @Override
    public String extractBarcodeFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getString("localBarcode");
    }

    @Override
    public String extractNameFromJsonObject(JSONObject jsonObject) {
        return getStringByKeyArray(jsonObject, new String[] {"names", "1", "long"});
    }

    @Override
    public boolean extractIsInKgFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getBoolean("isWeighable");
    }
}
