package com.example.rectrutmenttool.web;

import com.example.rectrutmenttool.model.dto.SkillDto;
import com.example.rectrutmenttool.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/skills")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @RequestMapping(
            value = "/{id}",
            produces = "application/json",
            method = RequestMethod.GET
    )
    public ResponseEntity<SkillDto> showSkill(@PathVariable Long id){
        SkillDto skill = skillService.showSkillById(id);

        if(skill != null){
            return ResponseEntity.ok(skill);
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(
            value = "/active",
            produces = "application/json",
            method = RequestMethod.GET
    )
    public ResponseEntity<Set<SkillDto>> showActiveSkills(){
        Set<SkillDto> activeSkills = skillService.getActiveSkills();

        if(activeSkills != null){
            return ResponseEntity.ok(activeSkills);
        }

        return ResponseEntity.notFound().build();
    }
}
