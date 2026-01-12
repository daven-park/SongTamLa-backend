package com.daven_park.SongTamLa.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "songtamla.exchange";
    public static final String QUEUE = "songtamla.extraction.request";
    public static final String ROUTING_KEY = "extraction.request";

    @Bean
    DirectExchange songtamlaExchange(){
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    Queue extractionRequestQueue() {
        return QueueBuilder.durable(QUEUE).build();
    }

    @Bean
    Binding extractionRequestBinding(DirectExchange songtamlaExchange, Queue extractionRequestQueue){
        return BindingBuilder.bind(extractionRequestQueue).to(songtamlaExchange).with(ROUTING_KEY);
    }

    @Bean
    MessageConverter jacksonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory cf, MessageConverter converter){
        RabbitTemplate template = new RabbitTemplate(cf);
        template.setMessageConverter(converter);
        return template;
    }

}
