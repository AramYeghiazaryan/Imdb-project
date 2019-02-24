package com.job.finalproject.finalproject.persistance.repository;

import com.job.finalproject.finalproject.persistance.entity.Rating;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    @Query(value = "SELECT r.reviewerid FROM rating r ORDER BY ABS((SELECT m.imdbrating FROM movie m WHERE r.movieid=m.id ) - r.reviewerating) LIMIT 2", nativeQuery = true)
    List<BigInteger> findReviewerIdByGrade();



    @Query(value = "CREATE TABLE IF NOT EXISTS movie_rating AS\n" +
            "SELECT m.*, r.crated, r.reviewerating, r.reviewerid FROM movie m INNER JOIN rating r ON m.id=r.movieid",nativeQuery = true)
     void createMovieRatingTable();

    @Query(value = "SELECT reviewerid FROM movie_rating  WHERE title LIKE %:title% AND ABS(imdbrating-reviewerating)=0.5 ",nativeQuery = true)
    List<BigInteger> getMovieIdByTitle(@Param("title") String title,Pageable pageable);
}
