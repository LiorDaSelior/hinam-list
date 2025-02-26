package com.hinamlist.hinam_list.service.json_scraper;

import java.util.Arrays;
import java.util.List;

public class RamiLeviScraperTest extends JsonScraperQuickTest<RamiLeviJsonScraper>{

    @Override
    protected RamiLeviJsonScraper createInstance() {
        return new RamiLeviJsonScraper();
    }

    @Override
    protected List<String> getCategoryIdTestList() {
        return Arrays.asList("195", "207", "226");
    }
}
