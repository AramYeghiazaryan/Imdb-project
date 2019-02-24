package com.job.finalproject.finalproject.persistance.repository;

import com.job.finalproject.finalproject.persistance.entity.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface GenreRepository  extends JpaRepository<Genre,Long> {

    List<Genre> getByType(String t,Pageable pageable);
}
