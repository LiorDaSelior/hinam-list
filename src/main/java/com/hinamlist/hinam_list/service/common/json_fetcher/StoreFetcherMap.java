package com.hinamlist.hinam_list.service.common.json_fetcher;

import com.hinamlist.hinam_list.config.StoreDataConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StoreFetcherMap {
    private final Map<String, IFetcher> storeNameFetcherMap;

    @Autowired
    public StoreFetcherMap(StoreDataConfigProperties storeDataConfigProperties,
                           List<IFetcher> fetcherList) {
        storeNameFetcherMap = new HashMap<>();
        for (IFetcher fetcher : fetcherList) {
            storeNameFetcherMap.put(fetcher.getStoreName(), fetcher);
        }
    }

    public IFetcher getFetcherByStoreName(String storeName) {
        return storeNameFetcherMap.get(storeName);
    }
}
