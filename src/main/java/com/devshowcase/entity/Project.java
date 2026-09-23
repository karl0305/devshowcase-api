package com.devshowcase.api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    private String repositoryUrl;

    private String deployUrl;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToMany
@JoinTable(
        name = "project_technologies",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "technology_id")
)
private List<Technology> technologies = new java.util.ArrayList<>();

    public Project() {
    }

    public Project(
            String title,
            String description,
            String repositoryUrl,
            String deployUrl,
            Profile profile) {

        this.title = title;
        this.description = description;
        this.repositoryUrl = repositoryUrl;
        this.deployUrl = deployUrl;
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }
}