package com.devshowcase.api.repository;

import com.devshowcase.api.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {

    @Query("""
        SELECT t
        FROM Technology t
        JOIN Project p ON t.id IN (
            SELECT tech.id
            FROM Project p2
            JOIN p2.technologies tech
            WHERE p2.id = :projectId
        )
    """)
    List<Technology> findByProjectId(@Param("projectId") Long projectId);
}