package org.example.cv_devops.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailedProjectDto {
    private UUID id;
    private String title;
    private String description;
    private List<MiniTechnologyDto> miniTechnologies;
    private String githubLink;
}
