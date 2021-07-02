package com.blackstar.jobag.company.domain.service;

import com.blackstar.jobag.company.domain.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface CompanyService {

    Company createCompany(Long employeerId, Long sectorId, Company companyRequest);
    Company updateCompany(Long employeerId, Long sectorId, Company companyRequest);
    ResponseEntity<?> deleteCompany(Long employeerId, Long sectorId);
    Company getCompanyById(Long companyId);
    Page<Company> getAllCompany(Pageable pageable);
}
