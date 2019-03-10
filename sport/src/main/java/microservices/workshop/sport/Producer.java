package microservices.workshop.sport;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Value("${sendKey}")
    private String sendKey;

    @Autowired
    private TopicExchange topic;

    @Autowired
    RabbitTemplate template;

    public void send(String msg){
        template.convertAndSend(topic.getName(), sendKey, msg);
    }

}
