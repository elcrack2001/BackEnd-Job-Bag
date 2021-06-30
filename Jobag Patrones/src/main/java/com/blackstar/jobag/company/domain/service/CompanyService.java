package com.blackstar.jobag.company.domain.service;

import com.blackstar.jobag.company.domain.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface CompanyService {
    Page<Company> getAllCompanysByEmployeerId(Long employeerId, Pageable pageable);
    Company getCompanyByIdAndEmployeerId(Long employeerId, Long companysId);
    Page<Company> getAllCompanysBySectorId(Long sectorId, Pageable pageable);
    Company getCompanyByIdAndSectorId(Long sectorId, Long companysId);

    Company createCompany(Long employeerId, Company company);
    Company updateCompany(Long employeerId, Long companyId, Company companyDetails);
    ResponseEntity<?> deleteCompany(Long employeerId,  Long companyId);
}
