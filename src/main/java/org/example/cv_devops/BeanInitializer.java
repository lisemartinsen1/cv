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
    public void run(String...args) {

        Technology t1 = Technology.builder()
                .technologyName("Java")
                .build();
        Technology t2 = Technology.builder()
                .technologyName("Spring Boot")
                .build();
        Technology t3 = Technology.builder()
                .technologyName("HTML")
                .build();
        Technology t4 = Technology.builder()
                .technologyName("CSS")
                .build();
        Technology t5 = Technology.builder()
                .technologyName("JS")
                .build();
        Technology t6 = Technology.builder()
                .technologyName("SQL")
                .build();
        Technology t7 = Technology.builder()
                .technologyName("Python")
                .build();
        Technology t8 = Technology.builder()
                .technologyName("Flask")
                .build();

        technologyRepo.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8));

        Project p1 = Project.builder()
                .title("Shoe Shop")
                .description("A project to manage shoe sales.")
                .technologies(Arrays.asList(t1, t2))
                .build();
        Project p2 = Project.builder()
                .title("Flask Application")
                .description("Focus on security, hash algorithms and how to crack passwords")
                .technologies(Arrays.asList(t7, t8))
                .build();
        Project p3 = Project.builder()
                .title("Spring Application")
                .description("Focus on security, hash algorithms and how to crack passwords")
                .technologies(Arrays.asList(t1, t2))
                .build();
        Project p4 = Project.builder()
                .title("Flask Application")
                .description("Focus on security, hash algorithms and how to crack passwords")
                .technologies(Arrays.asList(t7, t8))
                .build();
        Project p5 = Project.builder()
                .title("Flask Application")
                .description("Focus on security, hash algorithms and how to crack passwords")
                .technologies(Arrays.asList(t7, t8))
                .build();

        projectRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
    }
}
