package com.hinamlist.hinam_list.service.data_scraping.fetcher;

import com.hinamlist.hinam_list.service.data_scraping.json_scraper.CarrfourJsonScraper;
import org.json.JSONObject;

public class CarrfourFetcher extends AbstractFetcher<CarrfourJsonScraper>{
    protected CarrfourFetcher(CarrfourJsonScraper scraper) {
        super(scraper);
    }

    @Override
    protected int extractIdFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getInt("productId");
    }

    @Override
    protected String extractBarcodeFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getString("localBarcode");
    }

    @Override
    protected String extractNameFromJsonObject(JSONObject jsonObject) {
        return getStringByKeyArray(jsonObject, new String[] {"names", "1", "long"});
    }

    @Override
    protected boolean extractIsInKgFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getBoolean("isWeighable");
    }
}
