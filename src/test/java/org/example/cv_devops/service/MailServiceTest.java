package org.example.cv_devops.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;


@ActiveProfiles("test")
public class MailServiceTest {

    @Mock
    private JavaMailSender mailSender;
    @InjectMocks
    private MailService mailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMail() {
        String personalAddress = "test@example.com";
        String name = "Jane Doe";
        String emailAddress = "janedoe@example.com";
        String subject = "Test Subject";
        String message = "This is a test message";

        mailService.setPersonalAddress(personalAddress);
        mailService.sendMail(name, emailAddress, subject, message);

        ArgumentCaptor<SimpleMailMessage> mailMessageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(mailMessageCaptor.capture());
        SimpleMailMessage sentMessage = mailMessageCaptor.getValue();

        assertEquals(personalAddress, sentMessage.getFrom());
        assertEquals(personalAddress, Objects.requireNonNull(sentMessage.getTo())[0]);
        assertEquals("Contact form - " + subject, sentMessage.getSubject());
        assertEquals(
                "From: " + name + "\nEmail: " + emailAddress + "\nSubject: " + subject + "\nMessage: " + message,
                sentMessage.getText()
        );

    }
}
