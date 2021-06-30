package com.blackstar.jobag.interview.service;
;
import com.blackstar.jobag.exception.ResourceNotFoundException;
import com.blackstar.jobag.interview.domain.model.Interview;
import com.blackstar.jobag.interview.domain.repository.InterviewRepository;
import com.blackstar.jobag.interview.domain.service.InterviewService;
import com.blackstar.jobag.jobOffer.domain.repository.JobOfferRepository;
import com.blackstar.jobag.postulant.domain.repository.PostulantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InterviewServiceImpl implements InterviewService {
    @Autowired
    private PostulantRepository postulantRepository;
    @Autowired
    private JobOfferRepository jobOfferRepository;
    @Autowired
    private InterviewRepository interviewRepository;

    @Override
    public Interview createInterview(Long postulantId, Long jobOfferId, Interview interview) {
        if(interviewRepository.existsByPostulantId(postulantId) && interviewRepository.existsByJobOfferId(jobOfferId))
            throw  new ResourceNotFoundException("El postulante ya tiene programado una entrevista con la oferta de trabajo");

        //Compruebo que exista el postulant
        if(!postulantRepository.existsById(postulantId))
            throw new ResourceNotFoundException("Postulant","Id",postulantId);

            // Comprobar si exisite el joboffer
        else if (!jobOfferRepository.existsById(jobOfferId))
            throw new ResourceNotFoundException("Job Offer","Id", jobOfferId);

        return postulantRepository.findById(postulantId).map(postulant -> {
            interview.setPostulant(postulant);
            //EMPLOYEERREPOSITORY
            jobOfferRepository.findById(jobOfferId).map(jobOffer -> {
                interview.setJobOffer(jobOffer);
                return interviewRepository.save(interview);
            }).orElseThrow(() -> new ResourceNotFoundException("Job Offer Id" + jobOfferId));
            return interviewRepository.save(interview);
        }).orElseThrow(() -> new ResourceNotFoundException("Postulant Id" + postulantId));
    }

    @Override
    public Interview updateInterview(Long postulantId, Long jobOfferId, Long interviewId, Interview interviewDetails) {
        //Compruebo que exista el postulant
        if(!postulantRepository.existsById(postulantId))
            throw new ResourceNotFoundException("Postulant","Id",postulantId);
        else if (!jobOfferRepository.existsById(jobOfferId))
            throw new ResourceNotFoundException("Job Offer","Id", jobOfferId);

        return interviewRepository.findById(interviewId).map(interview -> {
            interview.setDate_Interview(interviewDetails.getDate_Interview())
                    .setLink_Interview(interviewDetails.getLink_Interview())
                    .setLink_Interview(interviewDetails.getLink_Interview());
            return  interviewRepository.save(interview);
        }).orElseThrow(() -> new ResourceNotFoundException("Postulant Id" + postulantId + "Job Offer Id" + jobOfferId));
    }

    @Override
    public ResponseEntity<?> deleteInterview(Long postulantId, Long jobOfferId, Long interviewId) {
        //Compruebo que exista el postulant
        if(!postulantRepository.existsById(postulantId))
            throw new ResourceNotFoundException("Postulant","Id",postulantId);
        else if (!jobOfferRepository.existsById(jobOfferId))
            throw new ResourceNotFoundException("Job Offer","Id", jobOfferId);

        return interviewRepository.findById(interviewId).map(interview -> {
            interviewRepository.delete(interview);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Interview","Id",interviewId));
    }

    @Override
    public Page<Interview> getAllInterviewsByPostulantId(Long postulantId, Pageable pageable) {
        return interviewRepository.findByPostulantId(postulantId,pageable);
    }

    @Override
    public Page<Interview> getAllInterviewsByJobOfferId(Long jobOfferId, Pageable pageable) {
        return interviewRepository.findByJobOfferId(jobOfferId,pageable);
    }

    @Override
    public Page<Interview> getAllInterviewByPostulantIdAndJobOfferId(Long postulantId, Long jobOfferId, Pageable pageable) {
        return interviewRepository.findByPostulantIdAndJobOfferId(postulantId,jobOfferId,pageable);
    }
}