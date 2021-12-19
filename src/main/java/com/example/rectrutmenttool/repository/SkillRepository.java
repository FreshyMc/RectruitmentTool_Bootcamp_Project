package com.example.rectrutmenttool.repository;

import com.example.rectrutmenttool.model.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
    Optional<SkillEntity> findSkillEntityByName(String name);

    @Query("SELECT DISTINCT c.skills FROM CandidateEntity c")
    Optional<Set<SkillEntity>> findActiveSkills();
}
