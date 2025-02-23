package com.hinamlist.hinam_list.service.data_scraping.fetcher;

import com.hinamlist.hinam_list.service.data_scraping.json_scraper.RamiLeviJsonScraper;
import org.json.JSONObject;

public class RamiLeviFetcher extends AbstractFetcher<RamiLeviJsonScraper>{
    public RamiLeviFetcher(RamiLeviJsonScraper scraper) {
        super(scraper);
    }

    @Override
    protected int extractIdFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getInt("id");
    }

    @Override
    protected String extractBarcodeFromJsonObject(JSONObject jsonObject) {
        return String.valueOf(jsonObject.getLong("barcode"));
    }

    @Override
    protected String extractNameFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getString("name");
    }

    @Override
    protected boolean extractIsInKgFromJsonObject(JSONObject jsonObject) {
        return (getIntByKeyArray(jsonObject, new String[] {"prop", "by_kilo"}) == 1);
    }
}
