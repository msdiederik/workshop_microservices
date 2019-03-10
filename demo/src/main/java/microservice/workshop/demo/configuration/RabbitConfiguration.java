package microservice.workshop.demo.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RabbitConfiguration {

    @Value("${topic.exchange}")
    private String exchange;

    @Value("${queue.name}")
    private String queueName;

    @Value("${queue.routingKey}")
    private String routingKey;

    @Bean
    public TopicExchange topic(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue newsQueue(){
        return new Queue("news");
    }

    @Bean
    public Binding newsBinding(Queue newsQueue, TopicExchange topic){
        return BindingBuilder.bind(newsQueue).to(topic).with(routingKey);
    }

    @Bean
    @Scope("singleton")
    public List<String> messagesSingleton(){
        return new ArrayList<>();
    }
}
