package com.hinamlist.hinam_list.config;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@TestConfiguration
@ComponentScan(basePackages = {"com.hinamlist.hinam_list.service.json_producer",
        "com.hinamlist.hinam_list.service.json_scraper",
        "com.hinamlist.hinam_list.config"
},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = {PropertiesTestConfiguration.class})
)
public class PropertiesITConfiguration {
//    public ApplicationStartup mockApplicationStartup() {
//        return Mockito.mock(ApplicationStartup.class);
//    }
}
