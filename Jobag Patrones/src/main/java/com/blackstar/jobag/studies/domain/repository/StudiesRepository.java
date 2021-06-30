package com.blackstar.jobag.studies.domain.repository;

import com.blackstar.jobag.studies.domain.model.Studies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudiesRepository extends JpaRepository<Studies, Long> {
    public Page<Studies> findById(Long Id, Pageable pageable);
}
