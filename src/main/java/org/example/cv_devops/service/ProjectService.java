package org.example.cv_devops.service;

import org.example.cv_devops.dto.DetailedProjectDto;
import org.example.cv_devops.dto.MiniTechnologyDto;
import org.example.cv_devops.model.Project;
import org.example.cv_devops.model.Technology;
import org.example.cv_devops.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<DetailedProjectDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::projectToDetailedProjectDto)
                .collect(Collectors.toList());
    }

    public DetailedProjectDto projectToDetailedProjectDto(Project p) {
        return DetailedProjectDto.builder()
                .id(p.getId())
                .title(p.getTitle())
                .description(p.getDescription())
                .miniTechnologies(mapTechnologiesToMiniTechnologyDtos(p.getTechnologies()))
                .githubLink(p.getGithubLink())
                .build();
    }

    public List<MiniTechnologyDto> mapTechnologiesToMiniTechnologyDtos(List<Technology> t) {
        return t.stream().map(tt -> MiniTechnologyDto.builder()
                        .id(tt.getId())
                        .technologyName(tt.getTechnologyName())
                        .build())
                .collect(Collectors.toList());
    }
}
