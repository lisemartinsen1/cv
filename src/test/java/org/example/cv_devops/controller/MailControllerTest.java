package org.example.cv_devops.controller;

import org.example.cv_devops.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MailController.class)
public class MailControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MailService mailService;

    @Test
    void sendForm_returnsSuccessMessageWhenMailSent() throws Exception {
        when(mailService.sendMail(anyString(), anyString(), anyString(), anyString())).thenReturn(true);

        mockMvc.perform(post("/sendContactForm")
                        .param("name", "Jane Doe")
                        .param("email", "jane@example.com")
                        .param("subject", "subject")
                        .param("message", "message"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Your message has been sent successfully!"));
    }

    @Test
    void sendForm_returnsErrorMessageWhenMailNotSent() throws Exception {
        when(mailService.sendMail(anyString(), anyString(), anyString(), anyString())).thenReturn(false);

        mockMvc.perform(post("/sendContactForm")
                        .param("name", "Jane Doe")
                        .param("email", "jane@example.com")
                        .param("subject", "subject")
                        .param("message", "message"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Something went wrong, please try again later."));
    }
}
