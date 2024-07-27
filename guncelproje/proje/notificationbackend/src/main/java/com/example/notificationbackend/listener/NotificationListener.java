package com.example.notificationbackend.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @Autowired
    private JavaMailSender mailSender;

    @RabbitListener(queues = "notificationQueue")
    public void handleNotification(String message) {
        try {
            // Email gönderme işlemi
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("brkyaydn81@gmail.com"); // Alıcı e-posta adresi
            mailMessage.setSubject("Update İnformation");
            mailMessage.setText(message);
            mailSender.send(mailMessage);

            System.out.println("Email sent successfully: " + message);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
