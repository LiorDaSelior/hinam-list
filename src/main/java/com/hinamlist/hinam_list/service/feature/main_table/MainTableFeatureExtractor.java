package com.hinamlist.hinam_list.service.feature.main_table;

import com.hinamlist.hinam_list.config.StoreDataConfigProperties;
import com.hinamlist.hinam_list.model.json_consumer.main_table.MainTableProduct;
import com.hinamlist.hinam_list.service.common.json_fetcher.IFetcher;
import com.hinamlist.hinam_list.service.common.json_fetcher.StoreFetcherMap;
import com.hinamlist.hinam_list.service.feature.FeatureExtractor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainTableFeatureExtractor extends FeatureExtractor<MainTableProduct> {
    protected StoreFetcherMap storeFetcherMap;

    @Autowired
    public MainTableFeatureExtractor(StoreDataConfigProperties storeDataConfigProperties,
                                     StoreFetcherMap storeFetcherMap) {
        super(storeDataConfigProperties);
        this.storeFetcherMap = storeFetcherMap;
    }

    @Override
    protected MainTableProduct extractFromJsonDataHaziHinam(JSONObject jsonObject) {
        return extractUsingFetcher(jsonObject, "HaziHinam");
    }

    @Override
    protected MainTableProduct extractFromJsonDataRamiLevi(JSONObject jsonObject) {
        return extractUsingFetcher(jsonObject, "RamiLevi");
    }

    @Override
    protected MainTableProduct extractFromJsonDataCarrfour(JSONObject jsonObject) {
        return extractUsingFetcher(jsonObject, "Carrfour");
    }

    private MainTableProduct extractUsingFetcher(JSONObject jsonObject, String storeName) {
        IFetcher fetcher = storeFetcherMap.getFetcherByStoreName(storeName);
        return new MainTableProduct(
                storeDataConfigProperties.getStoreDatabaseId(storeName),
                fetcher.extractBarcodeFromJsonObject(jsonObject),
                fetcher.extractIdFromJsonObject(jsonObject)
        );
    }
}
