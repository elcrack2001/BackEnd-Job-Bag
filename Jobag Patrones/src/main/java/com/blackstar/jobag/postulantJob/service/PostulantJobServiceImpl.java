package com.blackstar.jobag.postulantJob.service;

import com.blackstar.jobag.exception.ResourceNotFoundException;
import com.blackstar.jobag.jobOffer.domain.repository.JobOfferRepository;
import com.blackstar.jobag.postulant.domain.repository.PostulantRepository;
import com.blackstar.jobag.postulantJob.domain.model.PostulantJob;
import com.blackstar.jobag.postulantJob.domain.repository.PostulantJobRepository;
import com.blackstar.jobag.postulantJob.domain.service.PostulantJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostulantJobServiceImpl implements PostulantJobService {
    @Autowired
    private PostulantRepository postulantRepository;
    @Autowired
    private PostulantJobRepository postulantJobRepository;
    @Autowired
    private JobOfferRepository jobOfferRepository;

    @Override
    public PostulantJob createPostulantJob(Long postulantId, Long jobOfferId, PostulantJob postulantJob) {
        if(postulantJobRepository.existsByPostulantId(postulantId) && postulantJobRepository.existsByJobOfferId(jobOfferId))
            throw  new ResourceNotFoundException("El postulante ya postulo a esta oferta de trabajo");

        if(!postulantRepository.existsById(postulantId))
            throw new ResourceNotFoundException("Postulant","Id",postulantId);

        else if (!jobOfferRepository.existsById(jobOfferId))
            throw new ResourceNotFoundException("Job Offer","Id", jobOfferId);

        return postulantRepository.findById(postulantId).map(postulant -> {
            postulantJob.setPostulant(postulant);
            jobOfferRepository.findById(jobOfferId).map(jobOffer -> {
                postulantJob.setJobOffer(jobOffer);
                return postulantJobRepository.save(postulantJob);
            }).orElseThrow(() -> new ResourceNotFoundException("Job Offer Id" + jobOfferId));
            return postulantJobRepository.save(postulantJob);
        }).orElseThrow(() -> new ResourceNotFoundException("Postulant Id" + postulantId));
    }

    @Override
    public PostulantJob updatePostulantJob(Long postulantId, Long jobOfferId, PostulantJob postulantJobDetails) {
        if(!postulantRepository.existsById(postulantId))
            throw new ResourceNotFoundException("Postulant","Id",postulantId);

        else if (!jobOfferRepository.existsById(jobOfferId))
            throw new ResourceNotFoundException("Job Offer","Id", jobOfferId);

        return postulantJobRepository.findByPostulantIdAndJobOfferId(postulantId, jobOfferId).map(postulantJob -> {
            postulantJob.setAceppt(postulantJobDetails.isAceppt());
            return  postulantJobRepository.save(postulantJob);
        }).orElseThrow(() -> new ResourceNotFoundException("Postulant Id" + postulantId + "Job Offer Id" + jobOfferId));
    }

    @Override
    public ResponseEntity<?> deletePostulantJob(Long postulantId, Long jobOfferId) {
        if(!postulantRepository.existsById(postulantId))
            throw new ResourceNotFoundException("Postulant","Id",postulantId);
        else if (!jobOfferRepository.existsById(jobOfferId))
            throw new ResourceNotFoundException("Job Offer","Id", jobOfferId);

        return postulantJobRepository.findByPostulantIdAndJobOfferId(postulantId, jobOfferId).map(postulantJob -> {
            postulantJobRepository.delete(postulantJob);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Postulant Id" + postulantId + "Job Offer Id" + jobOfferId));
    }

    @Override
    public PostulantJob getPostulantJobById(Long postulantJobId) {
        return postulantJobRepository.findById(postulantJobId)
                .orElseThrow(() -> new ResourceNotFoundException("Postulant Job", "Id", postulantJobId));
    }

    @Override
    public Page<PostulantJob> getAllPostulantJob(Pageable pageable) {
        return postulantJobRepository.findAll(pageable);
    }
}

