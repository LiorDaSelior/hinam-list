package com.hinamlist.hinam_list.service.feature;

import com.hinamlist.hinam_list.config.StoreDataConfigProperties;
import org.json.JSONObject;

public abstract class FeatureExtractor<T>{
    protected StoreDataConfigProperties storeDataConfigProperties;

    public FeatureExtractor (StoreDataConfigProperties storeDataConfigProperties) {
        this.storeDataConfigProperties = storeDataConfigProperties;
    }

    public T extractFromJsonDataByStore (JSONObject jsonObject, String storeName) {
        return switch (storeName) {
            case "HaziHinam" -> extractFromJsonDataHaziHinam(jsonObject);
            case "RamiLevi" -> extractFromJsonDataRamiLevi(jsonObject);
            case "Carrfour" -> extractFromJsonDataCarrfour(jsonObject);
            default -> null;
        };
    }

    protected abstract T extractFromJsonDataHaziHinam(JSONObject jsonObject);
    protected abstract T extractFromJsonDataRamiLevi(JSONObject jsonObject);
    protected abstract T extractFromJsonDataCarrfour(JSONObject jsonObject);
}
