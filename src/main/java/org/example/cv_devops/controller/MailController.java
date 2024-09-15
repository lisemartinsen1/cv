package org.example.cv_devops.controller;

import org.example.cv_devops.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/sendContactForm")
    public String sendForm(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message,
            Model model
    ) {
        mailService.sendMail(name, email, subject, message);
        model.addAttribute("successMess", "Thank you for your message!");
        return "index";
    }
}
