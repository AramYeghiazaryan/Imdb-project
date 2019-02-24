package com.job.finalproject.finalproject.persistance.repository;

import com.job.finalproject.finalproject.persistance.entity.TitleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TitleTypeRepository extends JpaRepository<TitleType, Long> {

}
