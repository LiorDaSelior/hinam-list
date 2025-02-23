package com.hinamlist.hinam_list.service.data_scraping.fetcher;

import com.hinamlist.hinam_list.service.data_scraping.json_scraper.HaziHinamJsonScraper;
import org.json.JSONObject;

import java.util.Objects;

public class HaziHinamFetcher extends AbstractFetcher<HaziHinamJsonScraper>{
    public HaziHinamFetcher(HaziHinamJsonScraper scraper) {
        super(scraper);
    }

    @Override
    protected int extractIdFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getInt("Id");
    }

    @Override
    protected String extractBarcodeFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getString("BarKod");
    }

    @Override
    protected String extractNameFromJsonObject(JSONObject jsonObject) {
        return jsonObject.getString("Name");
    }

    @Override
    protected boolean extractIsInKgFromJsonObject(JSONObject jsonObject) {
        return Objects.equals(jsonObject.getString("WeightUnitDesc"), "נמכר במשקל");
    }
}
