package com.blackstar.jobag.company.controller;

import com.blackstar.jobag.company.domain.model.Company;
import com.blackstar.jobag.company.domain.service.CompanyService;
import com.blackstar.jobag.company.resource.CompanyResource;
import com.blackstar.jobag.company.resource.SaveCompanyResource;
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
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ModelMapper mapper;

    @Operation(summary="Put companys", description="Update companys by employeer Id", tags={"companys"})
    @GetMapping("/employeers/{employeerId}/companys")
    public Page<CompanyResource> getAllCompanysByEmployeerId(@PathVariable Long employeerId, Pageable pageable) {
        Page<Company> companyPage = companyService.getAllCompanysByEmployeerId(employeerId, pageable);
        List<CompanyResource> resources = companyPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    @Operation(summary="Get companys", description="Get companys by employeer Id", tags={"companys"})
    @GetMapping("/employeers/{employeerId}/companys/{companyId}")
    public CompanyResource getCompanyByIdAndEmployeerId(@PathVariable Long employeerId, @PathVariable Long companyId) {
        return convertToResource(companyService.getCompanyByIdAndEmployeerId(employeerId, companyId));
    }


    @Operation(summary="Post companys", description="Create companys by employeer Id", tags={"companys"})
    @PostMapping("/employeers/{employeerId}/companys")
    public CompanyResource createCompany(
            @PathVariable Long employeerId,
            @Valid @RequestBody SaveCompanyResource resource) {
        return convertToResource(companyService.createCompany(employeerId, convertToEntity(resource)));
    }

    @Operation(summary="Put companys", description="Update companys by employeer Id", tags={"companys"})
    @PutMapping("/employeers/{employeerId}/companys/{companyId}")
    public CompanyResource updateCompany(
            @PathVariable Long employeerId,
            @PathVariable Long companyId,
            @Valid @RequestBody SaveCompanyResource resource) {
        return convertToResource(companyService.updateCompany(employeerId, companyId, convertToEntity(resource)));
    }

    @Operation(summary="Delete companys", description="Delete companys by employeer Id", tags={"companys"})
    @DeleteMapping("/employeers/{employeerId}/companys/{companyId}")
    public ResponseEntity<?> deleteCompany(
            @PathVariable Long employeerId,
            @PathVariable Long companyId) {
        return companyService.deleteCompany(employeerId, companyId);
    }
    private Company convertToEntity(SaveCompanyResource resource) {
        return mapper.map(resource, Company.class);
    }
    private CompanyResource convertToResource(Company entity) {
        return mapper.map(entity, CompanyResource.class);
    }
}
