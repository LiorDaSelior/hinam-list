package com.hinamlist.hinam_list;

import org.springframework.boot.SpringApplication;


public class HinamListApplication {
/*	@Bean
	Binding binding(Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(HinamListAppConfig.class, args);
	}

}
