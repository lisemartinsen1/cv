package org.example.cv_devops.service;

import org.example.cv_devops.dto.DetailedProjectDto;
import org.example.cv_devops.dto.MiniTechnologyDto;
import org.example.cv_devops.model.Project;
import org.example.cv_devops.model.Technology;
import org.example.cv_devops.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    private UUID projectId1;
    private UUID projectId2;
    private UUID techId1;
    private UUID techId2;

    private Technology tech1;
    private Technology tech2;
    private Project project1;
    private Project project2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectId1 = UUID.randomUUID();
        projectId2 = UUID.randomUUID();
        techId1 = UUID.randomUUID();
        techId2 = UUID.randomUUID();

        tech1 = Technology.builder().id(techId1).technologyName("Tech 1").build();
        tech2 = Technology.builder().id(techId2).technologyName("Tech 2").build();

        project1 = Project.builder()
                .id(projectId1)
                .title("Project 1")
                .description("Description 1")
                .technologies(List.of(tech1, tech2))
                .githubLink("https://github.com/sample/project1")
                .build();

        project2 = Project.builder()
                .id(projectId2)
                .title("Project 2")
                .description("Description 2")
                .technologies(List.of(tech1))
                .githubLink("https://github.com/project2")
                .build();
    }


    @Test
    void testProjectToDetailedProjectDto() {
        DetailedProjectDto detailedProjectDto = projectService.projectToDetailedProjectDto(project1);

        assertEquals(projectId1, detailedProjectDto.getId());
        assertEquals("Project 1", detailedProjectDto.getTitle());
        assertEquals("Description 1", detailedProjectDto.getDescription());
        assertEquals(2, detailedProjectDto.getMiniTechnologies().size());
        assertEquals("https://github.com/sample/project1", detailedProjectDto.getGithubLink());

        MiniTechnologyDto miniTechDto1 = detailedProjectDto.getMiniTechnologies().get(0);
        MiniTechnologyDto miniTechDto2 = detailedProjectDto.getMiniTechnologies().get(1);

        assertEquals(techId1, miniTechDto1.getId());
        assertEquals(techId2, miniTechDto2.getId());
        assertEquals("Tech 1", miniTechDto1.getTechnologyName());
        assertEquals("Tech 2", miniTechDto2.getTechnologyName());
    }

    @Test
    void testMapTechnologiesToMiniTechnologyDtos() {
        List<Technology> technologies = List.of(tech1, tech2);
        List<MiniTechnologyDto> miniTechnologyDtos = projectService.mapTechnologiesToMiniTechnologyDtos(technologies);

        assertEquals(2, miniTechnologyDtos.size());
        assertEquals(techId1, miniTechnologyDtos.get(0).getId());
        assertEquals(techId2, miniTechnologyDtos.get(1).getId());
        assertEquals("Tech 1", miniTechnologyDtos.get(0).getTechnologyName());
        assertEquals("Tech 2", miniTechnologyDtos.get(1).getTechnologyName());
    }


    @Test
    void testGetAllProjects() {

        when(projectRepository.findAll()).thenReturn(List.of(project1, project2));
        List<DetailedProjectDto> result = projectService.getAllProjects();

        assertEquals(2, result.size());

        assertEquals(project1.getId(), result.get(0).getId());
        assertEquals(project1.getTitle(), result.get(0).getTitle());
        assertEquals(project1.getDescription(), result.get(0).getDescription());
        assertEquals(project1.getTechnologies().size(), result.get(0).getMiniTechnologies().size());
        assertEquals(2, result.get(0).getMiniTechnologies().size());
        assertEquals(project1.getTechnologies().get(0).getTechnologyName(), result.get(0).getMiniTechnologies().get(0).getTechnologyName());
        assertEquals(project1.getTechnologies().get(1).getTechnologyName(), result.get(0).getMiniTechnologies().get(1).getTechnologyName());
        assertEquals(project1.getTechnologies().get(0).getId(), result.get(0).getMiniTechnologies().get(0).getId());
        assertEquals(project1.getTechnologies().get(1).getId(), result.get(0).getMiniTechnologies().get(1).getId());
        assertEquals(project1.getTechnologies().get(1).getTechnologyName(), result.get(0).getMiniTechnologies().get(1).getTechnologyName());
        assertEquals(project1.getGithubLink(), result.get(0).getGithubLink());

        assertEquals(project2.getId(), result.get(1).getId());
        assertEquals(project2.getTitle(), result.get(1).getTitle());
        assertEquals(project2.getDescription(), result.get(1).getDescription());
        assertEquals(1, result.get(1).getMiniTechnologies().size());
        assertEquals(project2.getTechnologies().get(0).getTechnologyName(), result.get(1).getMiniTechnologies().get(0).getTechnologyName());
        assertEquals(project2.getTechnologies().get(0).getId(), result.get(1).getMiniTechnologies().get(0).getId());
        assertEquals(project2.getGithubLink(), result.get(1).getGithubLink());


    }

}
