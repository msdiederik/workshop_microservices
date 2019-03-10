package microservice.workshop.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Consumer {

    @Autowired
    private List<String> binnenlandMessagesSingleton;

    @Autowired
    private List<String> sportMessagesSingleton;

    @Autowired
    private List<String> alertMessagesSingleton;

    @RabbitListener(queues = "#{binnenlandQueue.name}")
    public void binnenlandistener(String in){
        System.out.println("Message: "+in);
        binnenlandMessagesSingleton.add(in);
    }

    @RabbitListener(queues = "#{sportQueue.name}")
    public void sportListener(String in){
        System.out.println("Message: "+in);
        sportMessagesSingleton.add(in);
    }

    @RabbitListener(queues = "#{alertQueue.name}")
    public void alertListener(String in){
        System.out.println("Message: "+in);
        alertMessagesSingleton.add(in);
    }
}
