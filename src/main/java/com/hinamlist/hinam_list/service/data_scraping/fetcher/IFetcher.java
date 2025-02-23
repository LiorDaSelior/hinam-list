package com.hinamlist.hinam_list.service.data_scraping.fetcher;

import com.hinamlist.hinam_list.service.data_scraping.json_scraper.exception.APIResponseException;
import com.hinamlist.hinam_list.service.data_scraping.product.Product;

import java.io.IOException;
import java.util.List;

public interface IFetcher {
    public List<Product> getCategoryProductList(int categoryId) throws IOException, APIResponseException, InterruptedException;
    public List<Product> getProductList() throws IOException, APIResponseException, InterruptedException;
}
