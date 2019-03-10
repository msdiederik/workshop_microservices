package microservice.workshop.binnenland;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    Producer producer;

    @RabbitListener(queues = "#{queue.name}")
    public void listen(String in){
        String msg = "Nu in het binnenland: "+in;
        producer.send(msg);
    }
}
