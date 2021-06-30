package com.blackstar.jobag.employeer.service;

import com.blackstar.jobag.employeer.domain.model.Employeer;
import com.blackstar.jobag.employeer.domain.repository.EmployeerRepository;
import com.blackstar.jobag.employeer.domain.service.EmployeerService;
import com.blackstar.jobag.exception.ResourceNotFoundException;
import com.blackstar.jobag.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeerServiceImpl implements EmployeerService {

    @Autowired
    private EmployeerRepository employeerRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public Page<Employeer> getAllEmployeers(Pageable pageable) {
        return employeerRepository.findAll(pageable);
    }

    @Override
    public Employeer getEmployeerById(Long employeerId) {
        return employeerRepository.findById(employeerId)
                .orElseThrow(()->new ResourceNotFoundException("Employeer","Id",employeerId));
    }

    @Override
    public Employeer createEmployeer(Employeer employeer) {
        if (userRepository.existsByEmail(employeer.getEmail())) {
            throw new ResourceNotFoundException("El email ya esta en uso");
        }
        else if (userRepository.existsByNumber(employeer.getNumber())) {
            throw new ResourceNotFoundException("El numero ya esta en uso");
        }
        return employeerRepository.save(employeer);
    }

    @Override
    public ResponseEntity<?> deleteEmployeer(Long employeerId) {
        Employeer employeer=employeerRepository.findById(employeerId)
                .orElseThrow(() -> new ResourceNotFoundException("Employeer", "Id", employeerId));
        employeerRepository.delete(employeer);
        return ResponseEntity.ok().build();
    }
}
