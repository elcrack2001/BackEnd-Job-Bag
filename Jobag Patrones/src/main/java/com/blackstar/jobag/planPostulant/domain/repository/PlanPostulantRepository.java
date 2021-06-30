package com.blackstar.jobag.planPostulant.domain.repository;

import com.blackstar.jobag.planPostulant.domain.model.PlanPostulant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanPostulantRepository extends JpaRepository<PlanPostulant,Long> {
    Page<PlanPostulant> findByPostulantId(Long postulantId, Pageable pageable);
    public Optional<PlanPostulant> findByIdAndPostulantId(Long id, Long postulantId);
}
