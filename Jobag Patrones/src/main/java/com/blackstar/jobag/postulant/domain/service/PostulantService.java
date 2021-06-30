package com.blackstar.jobag.postulant.domain.service;

import com.blackstar.jobag.postulant.domain.model.Postulant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PostulantService {
    Page<Postulant> getAllPostulants(Pageable pageable);
    Postulant getPostulantById(Long postulantId);
    Postulant createPostulant(Postulant postulant );
    Postulant updatePostulant(Long postulantId, Postulant postulantRequest);
    ResponseEntity<?> deletePostulant(Long postulantId);
}
