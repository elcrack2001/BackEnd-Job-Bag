package com.blackstar.jobag.postulant.service;

import com.blackstar.jobag.exception.ResourceIncorrectData;
import com.blackstar.jobag.exception.ResourceNotFoundException;
import com.blackstar.jobag.postulant.domain.model.Postulant;
import com.blackstar.jobag.postulant.domain.repository.PostulantRepository;
import com.blackstar.jobag.postulant.domain.service.PostulantService;
import com.blackstar.jobag.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostulantServiceImpl implements PostulantService {
    @Autowired
    private PostulantRepository postulantRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Postulant> getAllPostulants(Pageable pageable) {
        return postulantRepository.findAll(pageable);
    }

    @Override
    public Postulant getPostulantById(Long postulantId) {
        return postulantRepository.findById(postulantId)
                .orElseThrow(()->new ResourceNotFoundException("Postulant","Id",postulantId));
    }

    @Override
    public Postulant createPostulant(Postulant postulant) {
        if(userRepository.existsByEmail(postulant.getEmail()))
        {
            throw  new ResourceIncorrectData("El email ya esta en uso");
        }
        return postulantRepository.save(postulant);
    }

    @Override
    public Postulant updatePostulant(Long postulantId, Postulant postulantRequest) {
        Postulant postulant = postulantRepository.findById(postulantId)
                .orElseThrow(() ->new ResourceNotFoundException("Postulant","Id",postulantId));
        return postulantRepository.save(

                postulant.setCivil_status(postulantRequest.getCivil_status())

        );
    }

    @Override
    public ResponseEntity<?> deletePostulant(Long postulantId) {
        Postulant postulant=postulantRepository.findById(postulantId)
                .orElseThrow(() -> new ResourceNotFoundException("Postulant", "Id", postulantId));
        postulantRepository.delete(postulant);
        return ResponseEntity.ok().build();
    }

    public PostulantRepository getPostulantRepository() {
        return postulantRepository;
    }

    public void setPostulantRepository(PostulantRepository postulantRepository) {
        this.postulantRepository = postulantRepository;
    }
}
