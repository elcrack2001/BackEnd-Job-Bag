package com.blackstar.jobag.professionalProfile.controller;

import com.blackstar.jobag.professionalProfile.domain.model.ProfessionalProfile;
import com.blackstar.jobag.professionalProfile.domain.service.ProfessionalProfileService;
import com.blackstar.jobag.professionalProfile.resource.ProfessionalProfileResource;
import com.blackstar.jobag.professionalProfile.resource.SaveProfessionalProfileResource;
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
public class ProfessionalProfileController {
    @Autowired
    private ProfessionalProfileService professionalprofileService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary="Get professionalprofile", description="Get all professionalprofiles", tags={"profiles"})
    @GetMapping("/postulants/{postulantId}/profiles")
    public Page<ProfessionalProfileResource> getAllProfessionalProfileByPostulantId(@PathVariable Long postulantId, Pageable pageable) {
        Page<ProfessionalProfile> professionalprofilePage = professionalprofileService.getAllProfessionalProfileByPostulantId(postulantId, pageable);
        List<ProfessionalProfileResource> resources = professionalprofilePage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary="Get professionalprofile", description="Get professionalprofile by postulant Id", tags={"profiles"})
    @GetMapping("/postulants/{postulantId}/profiles/{profileId}")
    public ProfessionalProfileResource getProfessionalProfileByIdAndPostulantId(@PathVariable Long postulantId, @PathVariable Long professionalprofileId) {
        return convertToResource(professionalprofileService.getProfessionalProfileByIdAndPostulantId(postulantId, professionalprofileId));
    }


    @Operation(summary="Post professionalprofile", description="Create professionalprofile", tags={"profiles"})
    @PostMapping("/postulants/{postulantId}/profiles")
    public ProfessionalProfileResource createProfessionalProfile(
            @PathVariable Long postulantId,@Valid @RequestBody SaveProfessionalProfileResource resource) {
        return convertToResource(professionalprofileService.createProfessionalProfile(postulantId, convertToEntity(resource)));
    }

    @Operation(summary="Put professionalprofile", description="Update professionalprofile", tags={"profiles"})
    @PutMapping("/postulants/{postulantId}/profiles/{profileId}")
    public ProfessionalProfileResource updateProfessionalProfile(
            @PathVariable Long postulantId,
            @PathVariable Long professionalprofileId,
            @Valid @RequestBody SaveProfessionalProfileResource resource) {
        return convertToResource(professionalprofileService.updateProfessionalProfile(postulantId, professionalprofileId, convertToEntity(resource)));
    }

    @Operation(summary="Delete professionalprofile", description="Delete professionalprofile", tags={"profiles"})
    @DeleteMapping("/postulants/{postulantId}/profiles/{profileId}")
    public ResponseEntity<?> deleteProfessionalProfile(
            @PathVariable Long postulantId,
            @PathVariable Long professionalprofileId) {
        return professionalprofileService.deleteProfessionalProfile(postulantId, professionalprofileId);
    }


    private ProfessionalProfile convertToEntity(SaveProfessionalProfileResource resource) {
        return mapper.map(resource, ProfessionalProfile.class);
    }

    private ProfessionalProfileResource convertToResource(ProfessionalProfile entity) {
        return mapper.map(entity, ProfessionalProfileResource.class);
    }

}
