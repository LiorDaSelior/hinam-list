package com.hinamlist.hinam_list.service.json_producer;

import com.hinamlist.hinam_list.service.json_scraper.exception.APIResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Profile("!test")
@Component
public class JsonProducerStarter {
    private final List<JsonProducer> jsonProducerList;

    @Autowired
    public JsonProducerStarter(List<JsonProducer> jsonProducerList) {
        this.jsonProducerList = jsonProducerList;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initJsonProducerSendData() throws IOException, InterruptedException, APIResponseException {
        for (JsonProducer producer : jsonProducerList) {
            producer.sendJSONData();
        }
    }
}
