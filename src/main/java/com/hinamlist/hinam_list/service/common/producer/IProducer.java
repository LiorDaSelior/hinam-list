package com.hinamlist.hinam_list.service.common.producer;

import com.hinamlist.hinam_list.service.common.json_fetcher.IFetcher;

public interface IProducer {
    void handleMessage(IFetcher fetcher, String message, int storeId, int producerId);
}
