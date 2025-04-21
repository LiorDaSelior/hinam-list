package com.hinamlist.hinam_list.service.feature.main_table;

import com.hinamlist.hinam_list.config.StoreDataConfigProperties;
import com.hinamlist.hinam_list.model.json_consumer.main_table.MainTableProduct;
import com.hinamlist.hinam_list.service.common.json_fetcher.HaziHinamFetcher;
import com.hinamlist.hinam_list.service.common.json_fetcher.CarrfourFetcher;
import com.hinamlist.hinam_list.service.common.json_fetcher.RamiLeviFetcher;
import com.hinamlist.hinam_list.service.common.json_fetcher.IFetcher;
import com.hinamlist.hinam_list.service.feature.IStoreResponseProvider;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class MainTableStoreResponse implements IStoreResponseProvider<MainTableProduct> {
    protected Map<String, Function<JSONObject, MainTableProduct>> storeResponseMap;
    protected StoreDataConfigProperties storeDataConfigProperties;

    @Autowired
    public MainTableStoreResponse (StoreDataConfigProperties storeDataConfigProperties) {
        this.storeResponseMap = new HashMap<>();
        this.storeDataConfigProperties = storeDataConfigProperties;
        addToStoreResponseMap("HaziHinam", new HaziHinamFetcher());
        addToStoreResponseMap("Carrfour", new CarrfourFetcher());
        addToStoreResponseMap("RamiLevi", new RamiLeviFetcher());

    }

    private void addToStoreResponseMap(String storeName, IFetcher fetcher) {
        var info = storeDataConfigProperties.getStoreDataMap().get(storeName);
        storeResponseMap.put(
                info.messageStoreHeader(),
                createStoreResponseEntryUsingFetcher(info.databaseId(), fetcher)
        );
    }

    private Function<JSONObject, MainTableProduct> createStoreResponseEntryUsingFetcher(int storeDatabaseId, IFetcher fetcher) {
        return (jsonObject) -> {
            return new MainTableProduct(
                    storeDatabaseId,
                    fetcher.extractBarcodeFromJsonObject(jsonObject),
                    fetcher.extractIdFromJsonObject(jsonObject)
            );
        };
    }

    @Override
    public Map<String, Function<JSONObject, MainTableProduct>> getStoreResponseMap() {
        return storeResponseMap;
    }
}
