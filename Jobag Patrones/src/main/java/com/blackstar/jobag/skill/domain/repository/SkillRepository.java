package com.blackstar.jobag.skill.domain.repository;

import com.blackstar.jobag.skill.domain.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
    public Page<Skill> findById(Long Id, Pageable page);
}
