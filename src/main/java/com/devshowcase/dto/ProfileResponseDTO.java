package com.devshowcase.api.dto;

public class ProfileResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String bio;
    private String githubUrl;
    private String linkedinUrl;

    public ProfileResponseDTO() {
    }

    public ProfileResponseDTO(
            Long id,
            String name,
            String email,
            String bio,
            String githubUrl,
            String linkedinUrl) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.githubUrl = githubUrl;
        this.linkedinUrl = linkedinUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }
}