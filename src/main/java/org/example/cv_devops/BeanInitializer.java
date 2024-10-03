package org.example.cv_devops;

import org.example.cv_devops.model.Project;
import org.example.cv_devops.model.Technology;
import org.example.cv_devops.repository.ProjectRepository;
import org.example.cv_devops.repository.TechnologyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;


@Component
public class BeanInitializer implements CommandLineRunner {

    private final ProjectRepository projectRepo;
    private final TechnologyRepository technologyRepo;

    public BeanInitializer(ProjectRepository projectRepo, TechnologyRepository technologyRepo) {
        this.projectRepo = projectRepo;
        this.technologyRepo = technologyRepo;
    }

    @Override
    public void run(String... args) {

        Technology java = Technology.builder()
                .technologyName("Java")
                .build();
        Technology springBoot = Technology.builder()
                .technologyName("Spring Boot")
                .build();
        Technology functionalProgramming = Technology.builder()
                .technologyName("Functional Programming")
                .build();
        Technology springSecurity = Technology.builder()
                .technologyName("Spring Security")
                .build();
        Technology html = Technology.builder()
                .technologyName("HTML")
                .build();
        Technology css = Technology.builder()
                .technologyName("CSS")
                .build();
        Technology js = Technology.builder()
                .technologyName("JS")
                .build();
        Technology restAPI = Technology.builder()
                .technologyName("Rest API")
                .build();
        Technology sql = Technology.builder()
                .technologyName("SQL")
                .build();
        Technology python = Technology.builder()
                .technologyName("Python")
                .build();
        Technology flask = Technology.builder()
                .technologyName("Flask")
                .build();

        technologyRepo.saveAll(Arrays.asList(java, springBoot, functionalProgramming, springSecurity, html, css, js, restAPI, sql, python, flask));

        Project p1 = Project.builder()
                .title("Shoe Shop")
                .description("A project to manage shoe sales.")
                .technologies(Arrays.asList(java, sql, functionalProgramming))
                .githubLink("https://github.com/lisemartinsen1/ShoeShop")
                .build();
        Project p2 = Project.builder()
                .title("Flask Application")
                .description("Focus on security, hash algorithms and how to crack passwords")
                .technologies(Arrays.asList(python, flask))
                .githubLink("https://github.com/lisemartinsen1/python_hash_project")
                .build();
        Project p3 = Project.builder()
                .title("Spring Security Application")
                .description("Focus on security, hash algorithms and how to crack passwords")
                .technologies(Arrays.asList(java, springSecurity, springBoot))
                .githubLink("https://github.com/lisemartinsen1/ItSec-Ind")
                .build();
        Project p4 = Project.builder()
                .title("Webshop")
                .description("A webshop with functional shopping cart.")
                .technologies(Arrays.asList(html, css, js, restAPI))
                .githubLink("https://github.com/lisemartinsen1/Webbshop_VG")
                .build();
        Project p5 = Project.builder()
                .title("Booking System")
                .description("A booking system for a hotel.")
                .technologies(Arrays.asList(java, springBoot, springSecurity, restAPI, sql))
                .githubLink("https://github.com/Jatteliten/backend_projektarbete")
                .build();

        projectRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
    }
}
