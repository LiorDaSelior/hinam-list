package com.hinamlist.hinam_list.service.json_consumer.base;

import com.hinamlist.hinam_list.service.common.json_fetcher.IFetcher;
import com.hinamlist.hinam_list.service.common.producer.IProducer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import java.nio.charset.StandardCharsets;

abstract class JsonConsumer implements IJsonConsumer {
    public static int static_id = 0;
    public int actual_id = static_id++;

    //@Autowired
    public final FanoutExchange exchange;
    //@Autowired
    public final RabbitAdmin admin;
    //public final String queue_name;
    public final IFetcher fetcher;
    public final int storeId;
    public final IProducer producer;

    public JsonConsumer(
            FanoutExchange exchange,
            RabbitAdmin admin,
            IFetcher fetcher,
            int storeId,
            IProducer producer
    ) {
        this.storeId = storeId;
        this.fetcher = fetcher;
        this.exchange = exchange;
        this.admin = admin;
        this.producer = producer;

/*        admin.declareQueue(new Queue(queueName));
        Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, "", null);
        admin.declareBinding(binding);*/

        createListener(admin, admin.declareQueue(), exchange);
    }

    private void createListener(RabbitAdmin admin, Queue queue, FanoutExchange exchange) {
        admin.declareBinding(BindingBuilder.bind(queue).to(exchange));
        SimpleMessageListenerContainer listener = new SimpleMessageListenerContainer(admin.getRabbitTemplate().getConnectionFactory());
        listener.addQueues(queue);
        listener.setMessageListener(message -> {
            String messageBody = new String(message.getBody(), StandardCharsets.UTF_8);
            receiveMessage(messageBody);
        });
        listener.start();
    }

    public void receiveMessage(String message) {
        producer.handleMessage(fetcher, message, storeId, actual_id);
    }

}
