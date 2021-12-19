package com.example.rectrutmenttool.repository;

import com.example.rectrutmenttool.model.entity.JobEntity;
import com.example.rectrutmenttool.model.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {
    @Query("SELECT j FROM JobEntity j WHERE j.skills IN :skill")
    Optional<List<JobEntity>> findJobBySkill(@Param("skill") String skill);

    Optional<List<JobEntity>> findAllBySkillsIn(Set<SkillEntity> skills);
}
