package org.example.cv_devops.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;
    private String title;
    private String description;
    @ManyToMany
    @JoinTable(name = "project_technology",
    joinColumns = @JoinColumn(name = "project_id"),
    inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private List<Technology> technologies;
}
