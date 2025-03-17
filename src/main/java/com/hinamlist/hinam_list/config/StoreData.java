package com.hinamlist.hinam_list.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

public record StoreData(int databaseId, String exchangeName) {
}
