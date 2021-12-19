package com.example.rectrutmenttool.web;

import com.example.rectrutmenttool.model.dto.RecruiterDto;
import com.example.rectrutmenttool.service.RecruiterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recruiters")
public class RecruiterController {
    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @RequestMapping(
            value = "",
            produces = "application/json",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<RecruiterDto>> showRecruiters(@RequestParam(required = false) Integer level){
        if(level == null){
            List<RecruiterDto> recruiters = recruiterService.listRecruiters();

            if(recruiters != null){
                return ResponseEntity.ok(recruiters);
            }

            return ResponseEntity.badRequest().build();
        }else{
            List<RecruiterDto> recruiters = recruiterService.listLeveledRecruiters(level);

            if(recruiters != null){
                return ResponseEntity.ok(recruiters);
            }

            return ResponseEntity.badRequest().build();
        }
    }
}
