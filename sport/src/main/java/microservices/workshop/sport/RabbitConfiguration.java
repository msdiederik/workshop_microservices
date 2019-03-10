package microservices.workshop.sport;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public Queue queue(){
        return new Queue(queueName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange topic){
        return BindingBuilder.bind(queue).to(topic).with(routingKey);
    }

}
