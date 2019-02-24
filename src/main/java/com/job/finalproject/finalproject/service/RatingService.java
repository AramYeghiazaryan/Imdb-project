package com.job.finalproject.finalproject.service;


import com.job.finalproject.finalproject.persistance.entity.Movie;
import com.job.finalproject.finalproject.persistance.entity.Rating;
import com.job.finalproject.finalproject.persistance.entity.Reviewer;
import com.job.finalproject.finalproject.persistance.repository.MovieRepository;
import com.job.finalproject.finalproject.persistance.repository.RatingRepository;
import com.job.finalproject.finalproject.persistance.repository.ReviewerRepository;
import com.job.finalproject.finalproject.service.dto.MovieDTO;
import com.job.finalproject.finalproject.service.dto.RatingDTO;
import com.job.finalproject.finalproject.service.dto.ReviewerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final  MovieRepository movieRepository;
    private final ReviewerRepository reviewerRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, MovieRepository movieRepository, ReviewerRepository reviewerRepository) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
        this.reviewerRepository = reviewerRepository;
    }

    public RatingDTO addRating( int reviewerating,Date created, Long movieid, Long reviewerid){
        Rating rating=new Rating();
        rating.setReviewerating(reviewerating);
        rating.setCrated(created);
        Reviewer reviewer=reviewerRepository.getOne(reviewerid);
        rating.setReviewer(reviewer);
        Movie movie=movieRepository.getOne(movieid);
        rating.setMovie(movie);
        rating.setReviewerid(reviewerid);
        rating.setMovieid(movieid);
        ratingRepository.save(rating);
        return RatingDTO.mapEntityToDto(rating);
    }

    public RatingDTO updateRating(Rating rating){
        ratingRepository.save(rating);
        return RatingDTO.mapEntityToDto(rating);
    }

    public void deleteRating(Long id){
        ratingRepository.deleteById(id);
    }

    public List<RatingDTO> getAllRating(){
        return RatingDTO.mapEntityToDtos(ratingRepository.findAll());
    }
    public  RatingDTO getRatingById(Long id){
        return RatingDTO.mapEntityToDto(ratingRepository.getOne(id));
    }

    public MovieDTO addMovie(String code,Date releasdate, int runtime, String title, String url,  BigDecimal imdbrating,int numvotes, Long ratingid){
        Rating rating=ratingRepository.getOne(ratingid);
        Movie movie = new Movie();
        movie.setCode(code);
        movie.setReleasdate(releasdate);
        movie.setRuntime(runtime);
        movie.setTitle(title);
        movie.setUrl(url);
        movie.setNumvotes(numvotes);
        movie.setImdbrating(imdbrating);
        rating.setMovie(movie);
        return MovieDTO.mapEntityToDto(movieRepository.save(movie));
    }

    public MovieDTO updateMovie(Movie movie,Long ratingid) {
        Rating rating=ratingRepository.getOne(ratingid);
        movieRepository.save(movie);
        rating.setMovie(movie);

        return MovieDTO.mapEntityToDto(movie);
    }

    public void deleteMovie(Long id,Long ratingid) {
        Rating rating=ratingRepository.getOne(ratingid);
        rating.setMovie(null);
        movieRepository.deleteById(id);
    }

    public MovieDTO getMovie(Long ratingid) {
        Rating rating=ratingRepository.getOne(ratingid);
        return MovieDTO.mapEntityToDto(rating.getMovie());
    }


    public ReviewerDTO addReviewer(String username, Long ratingid ){
        Rating rating=ratingRepository.getOne(ratingid);
        Reviewer reviewer=new Reviewer();
        reviewer.setUsername(username);
        rating.setReviewer(reviewer);
        return ReviewerDTO.mapEntityToDto(reviewerRepository.save(reviewer));
    }

    public  ReviewerDTO updateReviewer(Reviewer reviewer,Long ratingid ){
        Rating rating=ratingRepository.getOne(ratingid);
        reviewerRepository.save(reviewer);
        rating.setReviewer(reviewer);
        return ReviewerDTO.mapEntityToDto(reviewer);
    }

    public void deleteReviewer(Long id, Long ratingid){
        Rating rating=ratingRepository.getOne(ratingid);
        reviewerRepository.deleteById(id);
        rating.setReviewer(null);
    }

    public ReviewerDTO getReviewer(Long ratingid){
        Rating rating=ratingRepository.getOne(ratingid);
        return ReviewerDTO.mapEntityToDto(rating.getReviewer());
    }


   /* public List<ReviewerDTO> findReviewerByGrade(){
        List<BigInteger> reviewerId=ratingRepository.findReviewerIdByGrade();
        List<ReviewerDTO> reviewerDTOS=new ArrayList<>();
        for(BigInteger id:reviewerId){
            reviewerDTOS.add(ReviewerDTO.mapEntityToDto(reviewerRepository.getOne(id.longValue())));
        }
        return reviewerDTOS;
    }*/



}
