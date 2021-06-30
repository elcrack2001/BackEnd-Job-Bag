package com.blackstar.jobag.interview.controller;

import com.blackstar.jobag.interview.domain.model.Interview;
import com.blackstar.jobag.interview.domain.service.InterviewService;
import com.blackstar.jobag.interview.resource.InterviewResource;
import com.blackstar.jobag.interview.resource.SaveInterviewResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;
    @Autowired
    private ModelMapper mapper;

    @Operation(summary="Create interviews", description="Create interviews", tags={"interviews"})
    @PostMapping("/postulants/{postulantId}/joboffers/{jobofferId}/interviews")
    public InterviewResource createInterview(
            @PathVariable Long postulantId,
            @PathVariable Long jobofferId,
            @Valid @RequestBody SaveInterviewResource resource) {
        return convertToResource(interviewService.createInterview(postulantId,jobofferId,convertToEntity(resource)));
    }

    @Operation(summary="Delete interviews by Postulant ID and Job Offer ID", description="Delete interviews by Postulant ID and Job Offer ID", tags={"interviews"})
    @DeleteMapping("/postulants/{postulantId}/joboffers/{jobofferId}/interviews/{interviewId}")
    public ResponseEntity<?> deleteInterview(
            @PathVariable Long postulantId,
            @PathVariable Long jobofferId,
            @PathVariable Long interviewId) {
        return interviewService.deleteInterview(postulantId, jobofferId, interviewId);
    }

    @Operation(summary="Update interviews by postulant Id and job offer Id", description="Update interviews by postulant Id and job offer Id", tags={"interviews"})
    @PutMapping("/postulants/{postulantId}/joboffers/{jobofferId}/interviews/{interviewId}")
    public InterviewResource updateInterview(
            @PathVariable Long postulantId,
            @PathVariable Long jobofferId,
            @PathVariable Long interviewId,
            @Valid @RequestBody SaveInterviewResource resource) {
        return convertToResource(interviewService.updateInterview(postulantId, jobofferId, interviewId, convertToEntity(resource)));
    }

    @Operation(summary="Get interviews", description="Get all interviews by postulant Id", tags={"interviews"})
    @GetMapping("/postulants/{postulantId}/interviews")
    public Page<InterviewResource> getAllInterviewsByPostulantId(@PathVariable Long postulantId, Pageable pageable) {
        Page<Interview> interviewPage = interviewService.getAllInterviewsByPostulantId(postulantId, pageable);
        List<InterviewResource> resources = interviewPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary="Get interviews", description="Get all interviews by postulant Id", tags={"interviews"})
    @GetMapping("/joboffers/{jobofferId}/interviews")
    public Page<InterviewResource> getAllInterviewsByJobOfferId(
            @PathVariable Long jobofferId,
            Pageable pageable) {
        Page<Interview> interviewPage = interviewService.getAllInterviewsByJobOfferId(jobofferId, pageable);
        List<InterviewResource> resources = interviewPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary="Get interviews by postulant Id and job offer Id", description="Get interviews by postulant Id and job offer Id", tags={"interviews"})
    @GetMapping("/postulants/{postulantId}/joboffers/{jobofferId}/interviews")
    public Page<InterviewResource> getAllInterviewByPostulantIdAndJobOfferId(
            @PathVariable Long postulantId,
            @PathVariable Long jobofferId,
            Pageable pageable) {
        Page<Interview> interviewPage = interviewService.getAllInterviewByPostulantIdAndJobOfferId(postulantId, jobofferId, pageable);
        List<InterviewResource> resources = interviewPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Interview convertToEntity(SaveInterviewResource resource){
        return mapper.map(resource, Interview.class);
    }

    private InterviewResource convertToResource(Interview entity){
        return mapper.map(entity,InterviewResource.class);
    }

}
