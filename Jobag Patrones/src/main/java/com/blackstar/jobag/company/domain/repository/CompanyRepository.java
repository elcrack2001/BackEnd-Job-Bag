package com.blackstar.jobag.company.domain.repository;

import com.blackstar.jobag.company.domain.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    public Page<Company> findById (Long Id, Pageable pageable);

    Optional<Company> findByEmployeerIdAndSectorId(Long EmployeerId, Long SectorId);

    Boolean existsByEmployeerId(Long employeerId);
}
