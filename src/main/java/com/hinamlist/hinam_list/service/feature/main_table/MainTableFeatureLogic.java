package com.hinamlist.hinam_list.service.feature.main_table;

import com.hinamlist.hinam_list.model.json_consumer.main_table.MainTableProduct;
import com.hinamlist.hinam_list.repository.json_consumer.main_table.MainTableRepo;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MainTableFeatureLogic implements Consumer<MainTableProduct> {
    private final MainTableRepo repo;

    @Autowired
    MainTableFeatureLogic(MainTableRepo repo) {
            this.repo = repo;
        }

    @Override
    public void accept(MainTableProduct mainTableProduct) {
        repo.save(mainTableProduct);
    }
}
