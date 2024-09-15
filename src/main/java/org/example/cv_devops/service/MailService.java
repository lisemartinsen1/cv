package org.example.cv_devops.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String personalAddress;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String name, String emailAddress, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(personalAddress);
        mailMessage.setTo(personalAddress);
        mailMessage.setSubject("Contact form - " + subject);
        mailMessage.setText("From: " + name + "\nEmail: " + emailAddress + "\nSubject: " + subject + "\nMessage: " + message);

        mailSender.send(mailMessage);
    }
}
