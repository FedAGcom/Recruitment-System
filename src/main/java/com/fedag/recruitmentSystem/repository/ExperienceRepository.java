package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.Experience;
import com.fedag.recruitmentSystem.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    @Query(
            value = "select id, resume_id, description, start_date, end_date " +
                    "from experience where description = :description",
            nativeQuery = true
    )
    Optional<Experience> selectExperienceByDescription(
            @Param("description") String description);

    @Query(
            value = "select id, resume_id, description, start_date, end_date " +
                    "from experience where resume_id = :id",
            nativeQuery = true
    )
    Page<Experience> findByUserId(Pageable pageable,
                                  @Param("id")Long id);
}
