package com.kondziopl16;

import com.kondziopl16.notification.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final RabbitTemplate rabbitTemplate;


    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

//    @GetMapping("/message")
//    public String receiveMessage(){
//        Object message = rabbitTemplate.receiveAndConvert("kurs");
//        if(message!=null)
//        return "Udało sie pobrać wiadomość: "+ message.toString();
//        else
//            return "Nie ma wiadomości do odczytu";
//    }

    @GetMapping("/notification")
    public ResponseEntity<Notification> listenerNotification(){
        Notification notification = rabbitTemplate.
                receiveAndConvert("kurs", ParameterizedTypeReference.forType(Notification.class));
        if(notification != null)
        return ResponseEntity.ok(notification);
        return ResponseEntity.noContent().build();
    }

    @RabbitListener(queues = "kurs")
    public void listenerMessage(Notification notification){
        System.out.println(notification.getEmail()+" "+notification.getTitle());
    }
}
