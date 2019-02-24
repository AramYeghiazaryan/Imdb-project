package com.job.finalproject.finalproject.persistance.repository;


import com.job.finalproject.finalproject.persistance.entity.Director;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;


public interface DirectorRepository extends JpaRepository<Director,Long> {

    List<Director> findDirectorByFullnameContaining(String param,Pageable pageable);

}
