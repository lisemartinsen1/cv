package org.example.cv_devops.controller;

import org.example.cv_devops.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/sendContactForm")
    public ResponseEntity<Map<String, String>> sendForm(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message
    ) {
        boolean mailSent = mailService.sendMail(name, email, subject, message);
        Map<String, String> response = new HashMap<>();

        if (mailSent) {
            response.put("message", "Your message has been sent successfully!");
        } else {
            response.put("message", "Something went wrong, please try again later.");
        }
        return ResponseEntity.ok(response);
    }

}
