package com.devshowcase.api.repository;

import com.devshowcase.api.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Override
    @EntityGraph(attributePaths = {"technologies"})
    Page<Project> findAll(Pageable pageable);

    @Query("""
        SELECT DISTINCT p
        FROM Project p
        JOIN p.technologies t
        WHERE t.id = :technologyId
    """)
    Page<Project> findByTechnologyId(
            @Param("technologyId") Long technologyId,
            Pageable pageable);
}