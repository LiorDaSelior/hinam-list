package com.hinamlist.hinam_list.service.json_scraper;

import com.hinamlist.hinam_list.service.json_scraper.exception.APIResponseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HaziHinamScraperTest extends JsonScraperQuickTest<HaziHinamJsonScraper> {
    @Override
    protected HaziHinamJsonScraper createInstance() throws IOException, InterruptedException, APIResponseException {
        return new HaziHinamJsonScraper();
    }

    @Override
    protected List<String> getCategoryIdTestList() {
        return Arrays.asList("11780", "11500", "10870");
    }
}
