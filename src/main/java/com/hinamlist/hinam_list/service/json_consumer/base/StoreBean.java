package com.hinamlist.hinam_list.service.json_consumer.base;

import com.hinamlist.hinam_list.service.common.json_fetcher.IFetcher;
import org.springframework.amqp.core.FanoutExchange;

public class StoreBean {
    protected FanoutExchange exchange;
    protected IFetcher fetcher;
    protected int storeNumber;

    StoreBean(
            FanoutExchange exchange,
            IFetcher fetcher,
            int storeNumber
    ) {
        this.exchange = exchange;
        this.fetcher = fetcher;
        this.storeNumber = storeNumber;
    }

    public IFetcher getFetcher() {
        return fetcher;
    }

    public FanoutExchange getExchange() {
        return exchange;
    }

    public int getStoreNumber() {
        return storeNumber;
    }
}
