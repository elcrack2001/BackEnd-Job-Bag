package com.blackstar.jobag.company.service;

import com.blackstar.jobag.company.domain.model.Company;
import com.blackstar.jobag.company.domain.repository.CompanyRepository;
import com.blackstar.jobag.company.domain.service.CompanyService;
import com.blackstar.jobag.employeer.domain.repository.EmployeerRepository;
import com.blackstar.jobag.exception.ResourceNotFoundException;
import com.blackstar.jobag.sector.domain.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private EmployeerRepository employeerRepository;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company createCompany(Long employeerId, Long sectorId, Company company) {
        if (companyRepository.existsByEmployeerId(employeerId))
            throw  new ResourceNotFoundException("La compania ya fue registrado por el empleador");

        if(!employeerRepository.existsById(employeerId))
            throw new ResourceNotFoundException("Employeer","Id",employeerId);

        else if (!sectorRepository.existsById(sectorId))
            throw new ResourceNotFoundException("Sector","Id", sectorId);

        return employeerRepository.findById(employeerId).map(employeer -> {
            company.setEmployeer(employeer);
            sectorRepository.findById(sectorId).map(sector -> {
                company.setSector(sector);
                return companyRepository.save(company);
            }).orElseThrow(() -> new ResourceNotFoundException("Employeer Id" + employeer));
            return companyRepository.save(company);
        }).orElseThrow(() -> new ResourceNotFoundException("Sector Id" + sectorId));
    }

    @Override
    public Company updateCompany(Long employeerId, Long sectorId, Company companyRequest) {

        if(!employeerRepository.existsById(employeerId))
            throw new ResourceNotFoundException("Employeer","Id",employeerId);

        else if (!sectorRepository.existsById(sectorId))
            throw new ResourceNotFoundException("Sector","Id", sectorId);

        return companyRepository.findByEmployeerIdAndSectorId(employeerId,sectorId).map(company -> {
            company.setName(companyRequest.getName())
                    .setDirección(companyRequest.getDirección())
                    .setRuc(companyRequest.getRuc())
                    .setLogo(companyRequest.getLogo())
                    .setDescription(companyRequest.getDescription());
            return companyRepository.save(company);
        }).orElseThrow(() -> new ResourceNotFoundException("Employeer Id" + employeerId + "Sector Id" + sectorId));
    }

    @Override
    public ResponseEntity<?> deleteCompany(Long employeerId, Long sectorId) {

        if(!employeerRepository.existsById(employeerId))
            throw new ResourceNotFoundException("Employeer","Id",employeerId);

        else if (!sectorRepository.existsById(sectorId))
            throw new ResourceNotFoundException("Sector","Id", sectorId);

        return companyRepository.findByEmployeerIdAndSectorId(employeerId,sectorId).map(company -> {
            companyRepository.delete(company);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Employeer Id" +  employeerId + "Sector Id" + sectorId));
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "Id", companyId));
    }

    @Override
    public Page<Company> getAllCompany(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }
}
