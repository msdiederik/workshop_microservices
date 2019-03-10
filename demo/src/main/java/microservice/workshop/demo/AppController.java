package microservice.workshop.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private List<String> messagesSingleton;

    @Autowired
    private Producer producer;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("messages", messagesSingleton);
        model.addAttribute("messageForm", new MessageForm());
        return "home";
    }

    @PostMapping("/post")
    public String post(@ModelAttribute MessageForm messageForm, Model model){
        System.out.println("Routing key: "+ messageForm.getRoutingKey());
        System.out.println("MessageForm: "+ messageForm.getMessage());
        producer.send(messageForm.getRoutingKey(), messageForm.getMessage());
        return "redirect:/";
    }
}
