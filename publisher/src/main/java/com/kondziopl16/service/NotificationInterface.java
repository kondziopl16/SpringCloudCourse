package com.kondziopl16.service;

import com.kondziopl16.Student.Student;
import com.kondziopl16.notification.Notification;

public interface NotificationInterface {
    public Student getStudentData(long id);
    public Notification convertStudentToNotification(Student student);
}
