package com.kondziopl16.publisher;

import com.kondziopl16.Student.Student;
import com.kondziopl16.notification.Notification;
import com.kondziopl16.service.NotificationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    private NotificationService notificationService;
    private RabbitTemplate rabbitTemplate;

    public MessageController(RabbitTemplate rabbitTemplate, NotificationService notificationService) {
        this.notificationService = notificationService;
        this.rabbitTemplate = rabbitTemplate;
    }

    //    private final RabbitTemplate rabbitTemplate;
//
//    public MessageController(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @GetMapping("/message")
//    public String sendMessage(@RequestParam String message) {
//        rabbitTemplate.convertAndSend("kurs", message);
//        return "Wrzucono wiadomość na RabbitMQ";
//    }
//
//    @PostMapping("/notification")
//    public String sendNotification(@RequestBody Notification notification) {
//        rabbitTemplate.convertAndSend("kurs", notification);
//        return  "Wysłano notyfikację";
//    }

    @GetMapping ("/notifications/{id}")
    public void handleNotification(@PathVariable int id) {
        Student student = notificationService.getStudentData(id);
        Notification notification = notificationService.convertStudentToNotification(student);
        rabbitTemplate.convertAndSend("kurs", notification);
    }
}
