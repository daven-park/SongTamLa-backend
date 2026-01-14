package com.daven_park.SongTamLa.domain.job;

import com.daven_park.SongTamLa.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExtractionJobPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishExtractionRequested(Long jobId){
        var event = new ExtractionRequestedEvent(jobId);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, event);
    }
}
