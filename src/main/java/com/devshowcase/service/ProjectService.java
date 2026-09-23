package com.devshowcase.api.service;

import com.devshowcase.api.dto.ProjectRequestDTO;
import com.devshowcase.api.dto.ProjectResponseDTO;
import com.devshowcase.api.entity.Project;
import com.devshowcase.api.entity.Profile;
import com.devshowcase.api.entity.Technology;
import com.devshowcase.api.repository.ProjectRepository;
import com.devshowcase.api.repository.ProfileRepository;
import com.devshowcase.api.repository.TechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            ProfileRepository profileRepository,
            TechnologyRepository technologyRepository) {

        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
        this.technologyRepository = technologyRepository;
    }

    public ProjectResponseDTO create(ProjectRequestDTO dto) {

        Profile profile = profileRepository.findById(dto.getProfileId())
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        List<Technology> technologies = technologyRepository
                .findAllById(dto.getTechnologyIds());

        Project project = new Project(
                dto.getTitle(),
                dto.getDescription(),
                dto.getRepositoryUrl(),
                dto.getDeployUrl(),
                profile
        );project.setTechnologies(technologies);

        Project savedProject = projectRepository.save(project);

        return new ProjectResponseDTO(
                savedProject.getId(),
                savedProject.getTitle(),
                savedProject.getDescription(),
                savedProject.getRepositoryUrl(),
                savedProject.getDeployUrl(),
                savedProject.getProfile().getId(),
                technologies.stream()
                        .map(Technology::getId)
                        .toList()
        );
    }

   public List<ProjectResponseDTO> findAll() {

    return projectRepository.findAll()
            .stream()
            .map(project -> new ProjectResponseDTO(
                    project.getId(),
                    project.getTitle(),
                    project.getDescription(),
                    project.getRepositoryUrl(),
                    project.getDeployUrl(),
                    project.getProfile().getId(),
                    technologyRepository.findAllById(
                            project.getTechnologies()
                                    .stream()
                                    .map(Technology::getId)
                                    .toList()
                    )
                    .stream()
                    .map(Technology::getId)
                    .toList()
            ))
            .toList();
}
}