package com.daven_park.SongTamLa.domain.job;

import com.daven_park.SongTamLa.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExtractionJobListener {
    private final ExtractionWorkerService extractionWorkerService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void onMessage(ExtractionRequestedEvent event){
        extractionWorkerService.process(event.jobId());
    }
}
