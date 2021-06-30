package com.blackstar.jobag.sector.domain.repository;

import com.blackstar.jobag.employeer.domain.model.Employeer;
import com.blackstar.jobag.sector.domain.model.Sector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector,Long> {
    public Page<Employeer> findById(Long Id, Pageable page);
}
