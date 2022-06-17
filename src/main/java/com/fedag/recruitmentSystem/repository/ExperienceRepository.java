package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.Experience;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

  @Query(
      value = "select id, resume_id, description, start_date, end_date " +
          "from experience where description = :description",
      nativeQuery = true
  )
  Optional<Experience> selectExperienceByDescription(
      @Param("description") String description);
}
