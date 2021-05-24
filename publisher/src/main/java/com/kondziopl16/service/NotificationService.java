package com.kondziopl16.service;

import com.kondziopl16.Student.Student;
import com.kondziopl16.notification.Notification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService implements NotificationInterface{

    private RestTemplate restTemplate;

    public NotificationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Student getStudentData(long id) {
        Student student = restTemplate.exchange("http://localhost:8080/students/"+id, HttpMethod.GET, HttpEntity.EMPTY, Student.class).getBody();
        return student;
    }

    @Override
    public Notification convertStudentToNotification(Student student) {
        Notification notification = new Notification(student.getEmail(), "Witaj "+student.getLastName(), "Miło, że jesteś z nami "+student.getFirstName()+" "+student.getLastName());
        return notification;
    }
}
