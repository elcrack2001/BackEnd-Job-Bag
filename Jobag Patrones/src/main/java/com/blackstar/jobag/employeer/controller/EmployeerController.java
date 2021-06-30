package com.blackstar.jobag.employeer.controller;

import com.blackstar.jobag.employeer.domain.model.Employeer;
import com.blackstar.jobag.employeer.domain.service.EmployeerService;
import com.blackstar.jobag.employeer.resource.EmployeerResource;
import com.blackstar.jobag.employeer.resource.SaveEmployeerResource;
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
public class EmployeerController {
    @Autowired
    private EmployeerService employeerService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary="Get Employeers", description="Get All Employeers", tags={"employeers"})
    @GetMapping("/employeers")
    public Page<EmployeerResource> getAllEmployeers(Pageable pageable){
        Page<Employeer> employeerPage = employeerService.getAllEmployeers(pageable);
        List<EmployeerResource> resources = employeerPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary="Post Employeers", description="Create Employeers", tags={"employeers"})
    @PostMapping("/employeers")
    public EmployeerResource createEmployeer(@Valid @RequestBody SaveEmployeerResource resource) {
        Employeer employeer =convertToEntity(resource);
        return convertToResource(employeerService.createEmployeer(employeer));
    }

    @Operation(summary="Get EmployeersById", description="Get EmployeersById", tags={"employeers"})
    @GetMapping("/employeers/{id}")
    public EmployeerResource getEmployeerById(@PathVariable(name = "id") Long employeerId) {
        return convertToResource(employeerService.getEmployeerById(employeerId));
    }

    @Operation(summary="Delete Employeer By Id", description="DeleteEmployeerById", tags={"employeers"})
    @DeleteMapping("/employeers/{postId}}")

    public ResponseEntity<?> deleteEmployeer(@PathVariable Long postId) {
        return employeerService.deleteEmployeer(postId);
    }

    private Employeer convertToEntity(SaveEmployeerResource resource) {
        return mapper.map(resource, Employeer.class);
    }
    private EmployeerResource convertToResource(Employeer entity)
    {
        return mapper.map(entity, EmployeerResource.class);
    }

}
