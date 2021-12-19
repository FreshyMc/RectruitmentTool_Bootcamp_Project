package com.example.rectrutmenttool.web;

import com.example.rectrutmenttool.model.dto.JobDto;
import com.example.rectrutmenttool.model.service.JobServiceModel;
import com.example.rectrutmenttool.service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;
    private final ModelMapper modelMapper;

    public JobController(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(
            value = "",
            produces = "application/json",
            method = RequestMethod.POST
    )
    public ResponseEntity createJob(@RequestBody @Valid JobDto jobDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        JobServiceModel mapped = modelMapper.map(jobDto, JobServiceModel.class);

        jobService.createJob(mapped);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(
            value = "",
            produces = "application/json",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<JobDto>> searchJob(@RequestParam String skill){
        List<JobDto> jobs = jobService.searchBySkill(skill);

        if(jobs != null){
            return ResponseEntity.ok(jobs);
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(
            value = "/{id}",
            produces = "application/json",
            method = RequestMethod.DELETE
    )
    public ResponseEntity deleteJob(@PathVariable Long id){
        jobService.removeJob(id);

        return ResponseEntity.ok().build();
    }
}
