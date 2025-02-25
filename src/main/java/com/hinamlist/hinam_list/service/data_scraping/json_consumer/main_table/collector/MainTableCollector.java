package com.hinamlist.hinam_list.service.data_scraping.json_consumer.main_table.collector;

import com.hinamlist.hinam_list.model.json_consumer.main_table.MainTableProduct;
import com.hinamlist.hinam_list.repository.json_consumer.main_table.MainTableRepo;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "${rabbitmq.main-table-collector.queue}")
public class MainTableCollector {

    private MainTableRepo repo;

    MainTableCollector(MainTableRepo repo) {
        this.repo = repo;
    }

    @RabbitHandler
    public void receive(String recordJson) {
        JSONObject recordJsonObject = new JSONObject(recordJson);
        int id = recordJsonObject.getInt("id");
        String barcode = recordJsonObject.getString("barcode");
        int storeId = recordJsonObject.getInt("storeId");
        //System.out.printf("Collector: Received - (Store: %d, StoreId: %d, Barcode: %s)%n", storeId, id, barcode);
        repo.save(new MainTableProduct(storeId, barcode,id));
    }
}
