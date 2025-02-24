package com.hinamlist.hinam_list.service.data_scraping.json_consumer.base;

import com.hinamlist.hinam_list.service.data_scraping.common.IFetcher;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

abstract class JsonConsumer implements  IJsonConsumer {
    public static int static_id = 0;
    public int actual_id = static_id++;

    //@Autowired
    public final String exchangeName;
    //@Autowired
    public final RabbitAdmin admin;
    //public final String queue_name;
    public final String queueName;
    public final IFetcher fetcher;
    public final int storeId;

    public JsonConsumer(String queueName, String exchangeName, RabbitAdmin admin, IFetcher fetcher, int storeId) {
        this.storeId = storeId;
        this.fetcher = fetcher;
        this.exchangeName = exchangeName;
        this.admin = admin;
        this.queueName = queueName;
        //queue_name = admin.declareQueue(new Queue("queue_" + actual_id));
        admin.declareQueue(new Queue(queueName));
        Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, "", null);
        admin.declareBinding(binding);
    }

/*    @RabbitListener(queues = "{#queue.getName()}")*/
    public abstract void receiveMessage(String message);

}
