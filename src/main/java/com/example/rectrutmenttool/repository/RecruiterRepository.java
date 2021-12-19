package com.example.rectrutmenttool.repository;

import com.example.rectrutmenttool.model.entity.RecruiterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruiterRepository extends JpaRepository<RecruiterEntity, Long> {
    Optional<RecruiterEntity> findRecruiterEntityByEmail(String email);

    @Query("SELECT DISTINCT r FROM RecruiterEntity r JOIN CandidateEntity c ON r.id = c.recruiter.id")
    Optional<List<RecruiterEntity>> findActiveRecruiters();

    @Query("SELECT r FROM RecruiterEntity r WHERE r.level = :level")
    Optional<List<RecruiterEntity>> findRecruitersByLevel(@Param("level") Integer level);
}
