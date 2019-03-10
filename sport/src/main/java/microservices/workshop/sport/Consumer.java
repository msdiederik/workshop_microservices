package microservices.workshop.sport;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    Producer producer;

    @RabbitListener(queues = "#{queue.name}")
    public void listener(String in){
        String msg = "Nu in het sportnieuws: "+in;
        producer.send(msg);
    }
}
