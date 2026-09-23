package com.devshowcase.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ProjectRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    private String title;

    @NotBlank(message = "A descrição é obrigatória")
    private String description;

    @Pattern(
        regexp = "^(https?://).+$",
        message = "URL do repositório inválida"
    )
    private String repositoryUrl;

    @Pattern(
        regexp = "^(https?://).+$",
        message = "URL do deploy inválida"
    )
    private String deployUrl;

    @NotNull(message = "O perfil é obrigatório")
    private Long profileId;

    private List<Long> technologyIds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getDeployUrl() {
        return deployUrl;
    }

    public void setDeployUrl(String deployUrl) {
        this.deployUrl = deployUrl;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public List<Long> getTechnologyIds() {
        return technologyIds;
    }

    public void setTechnologyIds(List<Long> technologyIds) {
        this.technologyIds = technologyIds;
    }
}