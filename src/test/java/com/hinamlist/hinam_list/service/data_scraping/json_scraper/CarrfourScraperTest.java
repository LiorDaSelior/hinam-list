package com.hinamlist.hinam_list.service.data_scraping.json_scraper;

import java.util.Arrays;
import java.util.List;

public class CarrfourScraperTest  extends JsonScraperQuickTest<CarrfourJsonScraper>{

    @Override
    protected CarrfourJsonScraper createInstance() {
        return new CarrfourJsonScraper();
    }

    @Override
    protected List<String> getCategoryIdTestList() {
        return Arrays.asList("79619", "79687", "79821");
    }
}
