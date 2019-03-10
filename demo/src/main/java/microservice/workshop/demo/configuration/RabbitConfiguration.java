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

    @Value("${binnenlandQueue.name}")
    private String binnenlandQueueName;

    @Value("${binnenlandQueue.routingKey}")
    private String binnenlandRoutingKey;

    @Value("${sportQueue.name}")
    private String sportQueueName;

    @Value("${sportQueue.routingKey}")
    private String sportRoutingKey;

    @Value("${alertQueue.name}")
    private String alertQueueName;

    @Value("${alertQueue.routingKey}")
    private String alertRoutingKey;

    @Bean
    public TopicExchange topic(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue binnenlandQueue(){
        return new Queue(binnenlandQueueName);
    }

    @Bean
    public Binding binnenlandBinding(Queue binnenlandQueue, TopicExchange topic){
        return BindingBuilder.bind(binnenlandQueue).to(topic).with(binnenlandRoutingKey);
    }

    @Bean
    public Queue sportQueue(){
        return new Queue(sportQueueName);
    }

    @Bean
    public Binding sportBinding(Queue sportQueue, TopicExchange topic){
        return BindingBuilder.bind(sportQueue).to(topic).with(sportRoutingKey);
    }

    @Bean
    public Queue alertQueue(){
        return new Queue(alertQueueName);
    }

    @Bean
    public Binding alertBinding(Queue alertQueue, TopicExchange topic){
        return BindingBuilder.bind(alertQueue).to(topic).with(alertRoutingKey);
    }

    @Bean
    @Scope("singleton")
    public List<String> binnenlandMessagesSingleton(){
        return new ArrayList<>();
    }

    @Bean
    @Scope("singleton")
    public List<String> sportMessagesSingleton(){
        return new ArrayList<>();
    }

    @Bean
    @Scope("singleton")
    public List<String> alertMessagesSingleton(){
        return new ArrayList<>();
    }
}
