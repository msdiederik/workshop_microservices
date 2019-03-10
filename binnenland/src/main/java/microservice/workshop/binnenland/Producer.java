package microservice.workshop.binnenland;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    TopicExchange topic;

    @Value("${sendKey}")
    private String sendKey;

    @Autowired
    RabbitTemplate template;

    public void send(String message){
        template.convertAndSend(topic.getName(), sendKey, message);
    }
}
