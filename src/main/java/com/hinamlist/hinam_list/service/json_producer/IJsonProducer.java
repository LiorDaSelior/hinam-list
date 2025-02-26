package com.hinamlist.hinam_list.service.json_producer;

import com.hinamlist.hinam_list.service.json_scraper.exception.APIResponseException;
import org.json.JSONObject;

import java.io.IOException;

public interface IJsonProducer {
    public void sendJSONObject(JSONObject jsonObject);
    public void sendJSONData() throws IOException, InterruptedException, APIResponseException;
}
