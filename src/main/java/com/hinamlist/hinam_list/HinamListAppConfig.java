package com.hinamlist.hinam_list;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@EnableAutoConfiguration
@EnableAsync
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HinamListAppConfig {
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);  // Number of threads in the pool
        executor.setMaxPoolSize(20);   // Max number of threads
        executor.setQueueCapacity(100);  // Queue size before threads are added
        executor.setThreadNamePrefix("json-sender-");
        executor.initialize();
        return executor;
    }

    // Spring AMQP required beans
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

}
