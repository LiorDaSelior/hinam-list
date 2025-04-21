package com.hinamlist.hinam_list.service.feature;

import org.json.JSONObject;

import java.util.Map;
import java.util.function.Function;

public interface IStoreResponseProvider<T> {
    public Map<String, Function<JSONObject, T>> getStoreResponseMap();
}
