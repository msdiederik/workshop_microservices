package microservice.workshop.demo;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer{

    @Autowired
    private TopicExchange topic;

    @Autowired
    private RabbitTemplate template;

    public void send(String routingKey, String message){
        template.convertAndSend(topic.getName(), routingKey, message);
        System.out.println("Sending: "+message+" on "+routingKey);
    }
}
