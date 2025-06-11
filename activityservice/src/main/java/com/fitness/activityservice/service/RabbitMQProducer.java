package com.fitness.activityservice.service;

import com.fitness.activityservice.config.RabbitMQConfig;
import com.fitness.activityservice.model.Activity;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Activity message) {
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, message);
        System.out.println("Sent message: " + message);
    }
}
