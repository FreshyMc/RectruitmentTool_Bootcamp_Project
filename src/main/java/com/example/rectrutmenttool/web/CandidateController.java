package com.example.rectrutmenttool.web;

import com.example.rectrutmenttool.model.dto.CandidateDto;
import com.example.rectrutmenttool.model.service.CandidateServiceModel;
import com.example.rectrutmenttool.service.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/candidates")
public class CandidateController {
    private final CandidateService candidateService;
    private final ModelMapper modelMapper;

    public CandidateController(CandidateService candidateService, ModelMapper modelMapper) {
        this.candidateService = candidateService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(
            value = "",
            produces = "application/json",
            method = RequestMethod.POST
    )
    public ResponseEntity createCandidate(@RequestBody @Valid CandidateDto candidateDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        CandidateServiceModel mapped = modelMapper.map(candidateDto, CandidateServiceModel.class);

        try{
            candidateService.createCandidate(mapped);
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(
            value = "/{id}",
            produces = "application/json",
            method = RequestMethod.GET
    )
    public ResponseEntity<CandidateDto> listCandidates(@PathVariable Long id){
        CandidateDto candidate = candidateService.listCandidate(id);

        if(candidate != null){
            return ResponseEntity.ok(candidate);
        }

        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(
            value = "/{id}",
            produces = "application/json",
            method = RequestMethod.PUT
    )
    public ResponseEntity<CandidateDto> updateCandidate(@PathVariable Long id, @RequestBody @Valid CandidateDto candidateDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        CandidateServiceModel mapped = modelMapper.map(candidateDto, CandidateServiceModel.class);

        try{
            candidateService.updateCandidate(id, mapped);
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(
            value = "/{id}",
            produces = "application/json",
            method = RequestMethod.DELETE
    )
    public ResponseEntity removeCandidate(@PathVariable Long id){
        try{
            candidateService.removeCandidate(id);
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }
}
