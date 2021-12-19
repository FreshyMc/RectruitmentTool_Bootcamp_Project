package com.example.rectrutmenttool.service.impl;

import com.example.rectrutmenttool.exception.AlreadyCreatedObject;
import com.example.rectrutmenttool.model.dto.CandidateDto;
import com.example.rectrutmenttool.model.dto.SkillDto;
import com.example.rectrutmenttool.model.entity.CandidateEntity;
import com.example.rectrutmenttool.model.entity.RecruiterEntity;
import com.example.rectrutmenttool.model.entity.SkillEntity;
import com.example.rectrutmenttool.model.service.CandidateServiceModel;
import com.example.rectrutmenttool.repository.CandidateRepository;
import com.example.rectrutmenttool.repository.RecruiterRepository;
import com.example.rectrutmenttool.repository.SkillRepository;
import com.example.rectrutmenttool.service.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final RecruiterRepository recruiterRepository;
    private final SkillRepository skillRepository;
    private final ModelMapper modelMapper;

    public CandidateServiceImpl(CandidateRepository candidateRepository, RecruiterRepository recruiterRepository, SkillRepository skillRepository, ModelMapper modelMapper) {
        this.candidateRepository = candidateRepository;
        this.recruiterRepository = recruiterRepository;
        this.skillRepository = skillRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createCandidate(CandidateServiceModel mapped) {
        boolean alreadyRegistered = candidateRepository.findCandidateEntityByEmail(mapped.getEmail()).isPresent();

        if(alreadyRegistered){
            throw new AlreadyCreatedObject();
        }

        CandidateEntity newCandidate = new CandidateEntity();

        newCandidate.
                setFirstName(mapped.getFirstName()).
                setLastName(mapped.getLastName()).
                setEmail(mapped.getEmail()).
                setBio(mapped.getBio()).
                setBirthDate(mapped.getBirthDate());

        Set<SkillEntity> skills = matchSkills(mapped.getSkills());

        newCandidate.setSkills(skills);

        Optional<RecruiterEntity> recruiter = recruiterRepository.findRecruiterEntityByEmail(mapped.getRecruiter().getEmail());

        if(recruiter.isPresent()){
            RecruiterEntity recruiterEntity = recruiter.get();

            newCandidate.setRecruiter(recruiterEntity);

            recruiterEntity.setLevel(recruiterEntity.getLevel() + 1);

            recruiterRepository.save(recruiterEntity);

            candidateRepository.save(newCandidate);

            return;
        }

        RecruiterEntity newRecruiter = new RecruiterEntity();

        newRecruiter.
                setEmail(mapped.getRecruiter().getEmail()).
                setCountry(mapped.getRecruiter().getCountry()).
                setLastName(mapped.getRecruiter().getLastName());

        recruiterRepository.save(newRecruiter);

        newCandidate.setRecruiter(newRecruiter);

        candidateRepository.save(newCandidate);
    }

    @Override
    public List<CandidateDto> listCandidates() {
        List<CandidateDto> candidates = candidateRepository.findAll().stream().map(c -> {
            return modelMapper.map(c, CandidateDto.class);
        }).collect(Collectors.toList());

        return candidates;
    }

    @Override
    @Transactional
    public CandidateDto listCandidate(Long id) {
        Optional<CandidateEntity> candidate = candidateRepository.findById(id);

        if(candidate.isPresent()){
            CandidateEntity candidateEntity = candidate.get();

            CandidateDto mapped = modelMapper.map(candidateEntity, CandidateDto.class);

            Set<SkillDto> skills = candidateEntity.getSkills().stream().map(s -> {
                SkillDto mappedSkill = new SkillDto();

                mappedSkill.setName(s.getName());

                return mappedSkill;
            }).collect(Collectors.toSet());

            mapped.setSkills(skills);

            return mapped;
        }

        return null;
    }

    @Override
    public void removeCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    @Override
    public void updateCandidate(Long id, CandidateServiceModel mapped) {
        boolean alreadyRegistered = candidateRepository.findById(id).isPresent();

        if(!alreadyRegistered){
            throw new AlreadyCreatedObject();
        }

        CandidateEntity updateCandidate = candidateRepository.findById(id).get();

        updateCandidate.
                setFirstName(mapped.getFirstName()).
                setLastName(mapped.getLastName()).
                setEmail(mapped.getEmail()).
                setBio(mapped.getBio()).
                setBirthDate(mapped.getBirthDate());

        Set<SkillEntity> skills = matchSkills(mapped.getSkills());

        updateCandidate.setSkills(skills);

        Optional<RecruiterEntity> recruiter = recruiterRepository.findRecruiterEntityByEmail(mapped.getRecruiter().getEmail());

        if(recruiter.isPresent()){
            RecruiterEntity recruiterEntity = recruiter.get();

            updateCandidate.setRecruiter(recruiterEntity);

            recruiterEntity.setLevel(recruiterEntity.getLevel() + 1);

            recruiterRepository.save(recruiterEntity);

            candidateRepository.save(updateCandidate);

            return;
        }

        RecruiterEntity newRecruiter = new RecruiterEntity();

        newRecruiter.
                setEmail(mapped.getRecruiter().getEmail()).
                setCountry(mapped.getRecruiter().getCountry()).
                setLastName(mapped.getRecruiter().getLastName());

        recruiterRepository.save(newRecruiter);

        updateCandidate.setRecruiter(newRecruiter);

        candidateRepository.save(updateCandidate);
    }

    private Set<SkillEntity> matchSkills(Set<SkillDto> skills){
        Set<SkillEntity> skillsCollection = skills.stream().map(s -> {
            Optional<SkillEntity> skill = skillRepository.findSkillEntityByName(s.getName());

            if (skill.isPresent()) {
                return skill.get();
            } else {
                SkillEntity skillToSave = new SkillEntity();

                skillToSave.setName(s.getName());

                return skillRepository.save(skillToSave);
            }
        }).collect(Collectors.toSet());

        return skillsCollection;
    }
}
