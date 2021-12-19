package com.example.rectrutmenttool.repository;

import com.example.rectrutmenttool.model.entity.CandidateEntity;
import com.example.rectrutmenttool.model.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {
    Optional<CandidateEntity> findCandidateEntityByEmail(String email);

    Optional<List<CandidateEntity>> findAllBySkillsIn(Set<SkillEntity> skills);
}
