package com.job.finalproject.finalproject.persistance.repository;

import com.job.finalproject.finalproject.persistance.entity.Movie;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;


public interface MovieRepository extends JpaRepository<Movie,Long> {

 // @Query("SELECT m FROM movie  m WHERE m.title LIKE  %:title%")
  List<Movie> findByTitleContaining(String title,Pageable pageable);

 //"SELECT d.id FROM director AS d WHERE d.fullname LIKE concat( '%',:name,'%')"
 // @Query("SELECT m.movie_list_id FROM director_movie m WHERE m.director_list_id=(SELECT d.id FROM director d WHERE d.fullname LIKE %?1%)")
  @Query(value = "SELECT movie_id FROM movie_director  WHERE director_id IN (SELECT id FROM director WHERE fullname LIKE %:name%)",nativeQuery = true)
  List<BigInteger> findMovieIdByDirectorFullName(@Param("name") String name,Pageable pageable);

  @Query(value = "select movie_id from movie_genre where genre_id IN (select id from genre where type LIKE %:name%)",nativeQuery =true )
  List<BigInteger> findMovieIdByGenreType(@Param("name") String type,Pageable pageable);


  //TODO TOO LONG IN SPRING
  @Query(value = "SELECT * FROM  movie WHERE imdbrating>:grade",nativeQuery = true)
  List<Movie> findMovieTitleByGrade(@Param("grade") int grade,Pageable pageable);


  List<Movie> getMovieByTitle (String title,Pageable pageable);

  List<Movie> getMovieById(Long id, Pageable pageable);


}
