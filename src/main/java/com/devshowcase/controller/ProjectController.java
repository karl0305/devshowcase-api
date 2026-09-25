package com.devshowcase.api.controller;

import com.devshowcase.api.dto.ProjectRequestDTO;
import com.devshowcase.api.dto.ProjectResponseDTO;
import com.devshowcase.api.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponseDTO create(
            @Valid @RequestBody ProjectRequestDTO dto) {

        return projectService.create(dto);
    }

    @GetMapping
    public Page<ProjectResponseDTO> findAll(
            @RequestParam(required = false) Long technologyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return projectService.findAll(technologyId, pageable);
    }

    @PutMapping("/{projectId}/upvote")
    public void upvote(@PathVariable Long projectId) {
        projectService.upvote(projectId);
    }
}