package com.devshowcase.api.dto;

import java.util.List;

public class ProjectResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String repositoryUrl;
    private String deployUrl;
    private Long profileId;
    private List<Long> technologyIds;

    public ProjectResponseDTO() {
    }

    public ProjectResponseDTO(
            Long id,
            String title,
            String description,
            String repositoryUrl,
            String deployUrl,
            Long profileId,
            List<Long> technologyIds) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.repositoryUrl = repositoryUrl;
        this.deployUrl = deployUrl;
        this.profileId = profileId;
        this.technologyIds = technologyIds;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public String getDeployUrl() {
        return deployUrl;
    }

    public Long getProfileId() {
        return profileId;
    }

    public List<Long> getTechnologyIds() {
        return technologyIds;
    }
}