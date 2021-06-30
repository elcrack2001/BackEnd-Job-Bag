package com.blackstar.jobag.planEmployeer.domain.service;

import com.blackstar.jobag.planEmployeer.domain.model.Planemployeer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PlanemployeerService {
    Page<Planemployeer> getAllPlanemployeersByEmployeerId(Long employeerId, Pageable pageable);
    Planemployeer getPlanemployeerByIdAndEmployeerId(Long planemployeersId, Long employeerId);
    Planemployeer createPlanemployeer(Long employeerId, Planemployeer planemployeer);
    Planemployeer updatePlanemployeer(Long employeerId, Long planemployeerId, Planemployeer planemployeerDetails);
    ResponseEntity<?> deletePlanemployeer(Long employeerId, Long planemployeerId);
}
