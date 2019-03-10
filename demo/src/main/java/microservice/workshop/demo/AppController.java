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
    private List<String> binnenlandMessagesSingleton;

    @Autowired
    private List<String> sportMessagesSingleton;

    @Autowired
    private List<String> alertMessagesSingleton;

    @Autowired
    private Producer producer;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("messageForm", new MessageForm());
        return "home";
    }

    @GetMapping("/binnenland")
    public String binnenland(Model model){
        model.addAttribute("title", "Nieuws in het binnenland");
        model.addAttribute("messages", binnenlandMessagesSingleton);
        return "list";
    }

    @GetMapping("/sport")
    public String sport(Model model){
        model.addAttribute("title", "Sportnieuws");
        model.addAttribute("messages", sportMessagesSingleton);
        return "list";
    }

    @GetMapping("/alert")
    public String alert(Model model){
        model.addAttribute("title", "Nieuws updates");
        model.addAttribute("messages", alertMessagesSingleton);
        return "list";
    }

    @PostMapping("/post")
    public String post(@ModelAttribute MessageForm messageForm, Model model){
        System.out.println("Routing key: "+ messageForm.getRoutingKey());
        System.out.println("MessageForm: "+ messageForm.getMessage());
        producer.send(messageForm.getRoutingKey(), messageForm.getMessage());
        return "redirect:/";
    }
}
