package org.example.cv_devops.service;

import org.example.cv_devops.dto.MiniTechnologyDto;
import org.example.cv_devops.model.Technology;
import org.example.cv_devops.repository.TechnologyRepository;
import org.springframework.stereotype.Service;

@Service
public class TechnologyService {

    private final TechnologyRepository technologyRepo;

    public TechnologyService(TechnologyRepository technologyRepo) {
        this.technologyRepo = technologyRepo;
    }

    public MiniTechnologyDto technologyToMiniTechnologyDto(Technology t) {
        return MiniTechnologyDto.builder()
                .id(t.getId())
                .technologyName(t.getTechnologyName())
                .build();
    }

}
