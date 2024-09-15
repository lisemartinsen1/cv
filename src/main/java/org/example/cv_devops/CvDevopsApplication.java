package org.example.cv_devops;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CvDevopsApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            Dotenv dotenv = Dotenv.load();
            dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
        }
        SpringApplication.run(CvDevopsApplication.class, args);
    }

}
