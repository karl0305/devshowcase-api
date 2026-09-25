package com.devshowcase.api.service;

import com.devshowcase.api.dto.ProjectRequestDTO;
import com.devshowcase.api.dto.ProjectResponseDTO;
import com.devshowcase.api.entity.Project;
import com.devshowcase.api.entity.Profile;
import com.devshowcase.api.entity.Technology;
import com.devshowcase.api.repository.ProjectRepository;
import com.devshowcase.api.repository.ProfileRepository;
import com.devshowcase.api.repository.TechnologyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        List<Technology> technologies =
                technologyRepository.findAllById(dto.getTechnologyIds());

        Project project = new Project(
                dto.getTitle(),
                dto.getDescription(),
                dto.getRepositoryUrl(),
                dto.getDeployUrl(),
                profile
        );

        project.setTechnologies(technologies);

        Project savedProject = projectRepository.save(project);

        return toResponse(savedProject);
    }

    public Page<ProjectResponseDTO> findAll(
            Long technologyId,
            Pageable pageable) {

        Page<Project> projects;

        if (technologyId != null) {
            projects = projectRepository.findByTechnologyId(
                    technologyId,
                    pageable
            );
        } else {
            projects = projectRepository.findAll(pageable);
        }

        return projects.map(this::toResponse);
    }

    public void upvote(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        project.setUpvotes(project.getUpvotes() + 1);

        projectRepository.save(project);
    }

    private ProjectResponseDTO toResponse(Project project) {

        return new ProjectResponseDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getRepositoryUrl(),
                project.getDeployUrl(),
                project.getProfile().getId(),
                project.getTechnologies()
                        .stream()
                        .map(Technology::getId)
                        .toList(),
                project.getUpvotes(),
                project.getAverageRating()
        );
    }
}