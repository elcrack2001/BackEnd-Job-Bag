package com.blackstar.jobag.postulantJob.domain.service;

import com.blackstar.jobag.postulantJob.domain.model.PostulantJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PostulantJobService {

    PostulantJob createPostulantJob(Long postulantId, Long jobOfferId, PostulantJob postulantJob);
    PostulantJob updatePostulantJob(Long postulantId, Long jobOfferId, PostulantJob postulantJobRequest);
    ResponseEntity<?> deletePostulantJob(Long postulantId, Long jobOfferId);
    PostulantJob getPostulantJobById(Long postulantJobId);
    Page<PostulantJob> getAllPostulantJob(Pageable pageable);
}
