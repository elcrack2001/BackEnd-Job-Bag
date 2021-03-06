package com.blackstar.jobag.employeer.domain.repository;

import com.blackstar.jobag.employeer.domain.model.Employeer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeerRepository extends JpaRepository<Employeer,Long> {
    public Page<Employeer> findById(Long Id, Pageable page);
    public Optional<Employeer> findByPosicion(String posicion);
}
