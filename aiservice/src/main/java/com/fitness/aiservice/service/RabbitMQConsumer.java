package com.fitness.aiservice.service;

import com.fitness.aiservice.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveMessage(Activity activity) {
        log.info("activity: {}",activity);

        log.info("Processing activity with Id: {}",activity.getUserId());
    }
}
