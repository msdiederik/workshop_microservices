package microservice.workshop.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Consumer {

    @Autowired
    private List<String> messagesSingleton;

    @RabbitListener(queues = "#{newsQueue.name}")
    public void getAllListener(String in){
        System.out.println("Message: "+in);
        messagesSingleton.add(in);
    }
}
