package pl.adcom.sbpublisherrmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherMq {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/AddMessage")
    public String getMessage(@RequestParam String message) {
        rabbitTemplate.convertAndSend("eggs", message);

        return "sent";
    }

    @RabbitListener(queues = "eggs")
    public void rabbitListenerValue(String s){
        System.out.println(s);
    }

}
