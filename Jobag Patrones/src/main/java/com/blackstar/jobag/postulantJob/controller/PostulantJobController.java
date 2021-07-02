package com.blackstar.jobag.postulantJob.controller;


import com.blackstar.jobag.postulantJob.domain.model.PostulantJob;
import com.blackstar.jobag.postulantJob.domain.service.PostulantJobService;
import com.blackstar.jobag.postulantJob.resource.PostulantJobResource;
import com.blackstar.jobag.postulantJob.resource.SavePostulantJobResource;
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
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
public class PostulantJobController {
    @Autowired
    private PostulantJobService postulantJobService;
    @Autowired
    private ModelMapper mapper;


    @Operation(summary="Postulant Jobs", description="Create postulantjobs",  tags={"postulant_jobs"})
    @PostMapping("/postulants/{postulantId}/joboffers/{jobofferId}/postulantjobs")
    public PostulantJobResource createJobOffer(
            @PathVariable Long postulantId,
            @PathVariable Long jobofferId,
            @Valid @RequestBody SavePostulantJobResource resource) {
        return convertToResource(postulantJobService.createPostulantJob(postulantId,jobofferId,convertToEntity(resource)));
    }

    @Operation(summary="Put Postulant Jobs", description="Update postulantjobs",  tags={"postulant_jobs"})
    @PutMapping("/postulant/{postulantId}/joboffers/{jobofferId}/postulantjobs}")
    public PostulantJobResource updatePostulantJob(
            @PathVariable Long postulantId,
            @PathVariable Long jobofferId,
            @Valid @RequestBody SavePostulantJobResource resource) {
        return convertToResource(postulantJobService.updatePostulantJob(postulantId, jobofferId,convertToEntity(resource)));
    }

    @Operation(summary="Delete postulant job by postulant ID and job offer ID", description="Delete postulant job by postulant ID and job offer ID",  tags={"postulant_jobs"})
    @DeleteMapping("/postulants/{postulantId}/joboffers/{jobofferId}/postulantjobs")
    public ResponseEntity<?> deletePostulantJob(
            @PathVariable Long postulantId,
            @PathVariable Long jobofferId) {
        return postulantJobService.deletePostulantJob(postulantId, jobofferId);
    }

    @Operation(summary = "Get All Postulant Job", description = "Get All Postulant Job", tags = {"postulant_jobs"})
    @GetMapping("/postulantjobs")
    public Page<PostulantJobResource> getAllPostulantJob(Pageable pageable){
        Page<PostulantJob> postulantJobPage = postulantJobService.getAllPostulantJob(pageable);
        List<PostulantJobResource> resources = postulantJobPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary="Get Postulant Job by Id", description="Get Postulant Job by Id", tags={"postulant_jobs"})
    @GetMapping("/postulantjobs/{postulantJobId}")
    public PostulantJobResource getPostulantJobById(
            @PathVariable Long postulantJobId) {
        return convertToResource(postulantJobService.getPostulantJobById(postulantJobId));
    }

    private PostulantJob convertToEntity(SavePostulantJobResource resource){
        return mapper.map(resource, PostulantJob.class);
    }

    private PostulantJobResource convertToResource(PostulantJob entity){
        return mapper.map(entity,PostulantJobResource.class);
    }
}