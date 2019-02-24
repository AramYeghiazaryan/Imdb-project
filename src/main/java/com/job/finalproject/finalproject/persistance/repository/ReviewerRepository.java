package com.job.finalproject.finalproject.persistance.repository;

import com.job.finalproject.finalproject.persistance.entity.Reviewer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;

public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {


    List<Reviewer> getReviewerById(Long id, Pageable pageable);

}
