package com.devshowcase.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public Feedback() {
    }

    public Feedback(String comment, Integer rating, Project project) {
        this.comment = comment;
        this.rating = rating;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}