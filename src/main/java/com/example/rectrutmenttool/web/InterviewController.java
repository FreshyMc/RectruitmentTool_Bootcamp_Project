package com.example.rectrutmenttool.web;

import com.example.rectrutmenttool.model.dto.InterviewDto;
import com.example.rectrutmenttool.service.InterviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/interviews")
public class InterviewController {
    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @RequestMapping(
            value = "",
            produces = "application/json",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<InterviewDto>> showInterviews(){
        List<InterviewDto> interviews = interviewService.showInterviews();

        return ResponseEntity.ok(interviews);
    }
}
