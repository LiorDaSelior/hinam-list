package com.hinamlist.hinam_list.service.data_scraping.common.json_fetcher;

import org.json.JSONObject;

public abstract class AbstractFetcher implements IFetcher {
    private JSONObject getRelevantObjectByKeyArray(JSONObject jsonObject , String[] keyArray) {
        JSONObject currentJsonObject = jsonObject;
        for (int i = 0; i < keyArray.length - 1; i++) {
            currentJsonObject = currentJsonObject.getJSONObject(keyArray[i]);
        }
        return currentJsonObject;
    }
    protected String getStringByKeyArray(JSONObject jsonObject , String[] keyArray) {
        return getRelevantObjectByKeyArray(jsonObject, keyArray).getString(keyArray[keyArray.length - 1]);
    }
    protected int getIntByKeyArray(JSONObject jsonObject , String[] keyArray) {
        return getRelevantObjectByKeyArray(jsonObject, keyArray).getInt(keyArray[keyArray.length - 1]);
    }
}
