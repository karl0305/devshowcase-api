package com.devshowcase.api.service;

import com.devshowcase.api.dto.FeedbackRequestDTO;
import com.devshowcase.api.entity.Feedback;
import com.devshowcase.api.entity.Project;
import com.devshowcase.api.repository.FeedbackRepository;
import com.devshowcase.api.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ProjectRepository projectRepository;

    public FeedbackService(
            FeedbackRepository feedbackRepository,
            ProjectRepository projectRepository) {

        this.feedbackRepository = feedbackRepository;
        this.projectRepository = projectRepository;
    }

    public Feedback create(Long projectId, FeedbackRequestDTO dto) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        Feedback feedback = new Feedback(
                dto.getComment(),
                dto.getRating(),
                project
        );

        Feedback savedFeedback = feedbackRepository.save(feedback);

        updateAverageRating(project);

        return savedFeedback;
    }

    private void updateAverageRating(Project project) {

        List<Feedback> feedbacks =
                feedbackRepository.findByProjectId(project.getId());

        double average = feedbacks.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);

        project.setAverageRating(average);

        projectRepository.save(project);
    }
}