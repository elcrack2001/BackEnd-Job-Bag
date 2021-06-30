package com.blackstar.jobag.postulantJob.domain.repository;

import com.blackstar.jobag.postulantJob.domain.model.PostulantJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostulantJobRepository extends JpaRepository<PostulantJob, Long> {
    public Page<PostulantJob> findById (Long Id, Pageable pageable);
    public Optional<PostulantJob> findByPostulantIdAndJobOfferId(Long postulantId, Long jobOfferId);
    Page<PostulantJob> findByPostulantId(Long postulantId, Pageable pageable);
    Boolean existsByPostulantId(Long postulantId);
    Boolean existsByJobOfferId(Long jobOfferId);
}
