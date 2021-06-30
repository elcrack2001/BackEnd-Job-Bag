package com.blackstar.jobag.employeer.domain.service;

import com.blackstar.jobag.employeer.domain.model.Employeer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EmployeerService {
    Page<Employeer> getAllEmployeers(Pageable pageable);
    Employeer getEmployeerById(Long employeerId);
    Employeer createEmployeer(Employeer employeer );
    ResponseEntity<?> deleteEmployeer(Long employeerId);
}
