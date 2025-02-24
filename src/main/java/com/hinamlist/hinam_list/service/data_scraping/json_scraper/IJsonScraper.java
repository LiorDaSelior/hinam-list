package com.hinamlist.hinam_list.service.data_scraping.json_scraper;

import com.hinamlist.hinam_list.service.data_scraping.json_scraper.exception.APIResponseException;
import org.json.JSONArray;

import java.io.IOException;
import java.util.List;

public interface IJsonScraper {
    public List<String> getCategoryIdList() throws IOException, APIResponseException, InterruptedException; // TODO: reconsider returning JSON object
    public JSONArray getCategoryProductInfo(String categoryId) throws IOException, APIResponseException, InterruptedException;
}
