package com.job.finalproject.finalproject.service;

import com.job.finalproject.finalproject.persistance.entity.Movie;
import com.job.finalproject.finalproject.persistance.entity.Rating;
import com.job.finalproject.finalproject.persistance.entity.Reviewer;
import com.job.finalproject.finalproject.persistance.repository.MovieRepository;
import com.job.finalproject.finalproject.persistance.repository.RatingRepository;
import com.job.finalproject.finalproject.persistance.repository.ReviewerRepository;
import com.job.finalproject.finalproject.service.dto.RatingDTO;
import com.job.finalproject.finalproject.service.dto.ReviewerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ReviewerService {

    private final ReviewerRepository reviewerRepository;
    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;
    @Autowired
    public ReviewerService(ReviewerRepository reviewerRepository, MovieRepository movieRepository, RatingRepository ratingRepository) {
        this.reviewerRepository = reviewerRepository;
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
    }

    public ReviewerDTO addReviewer(String username){
        Reviewer reviewer=new Reviewer();
        reviewer.setUsername(username);
        return ReviewerDTO.mapEntityToDto(reviewerRepository.save(reviewer));
    }

    public ReviewerDTO updateReviewer(Reviewer reviewer){
        reviewerRepository.save(reviewer);
        return ReviewerDTO.mapEntityToDto(reviewer);
    }

    public void deletReviewer(Long id){
        reviewerRepository.deleteById(id);
    }

    public List<ReviewerDTO> getAllReviewer(){
        return ReviewerDTO.mapEntityToDtos(reviewerRepository.findAll());
    }

    public ReviewerDTO getReviewerById(Long id){
        return ReviewerDTO.mapEntityToDto(reviewerRepository.getOne(id));
    }

    public RatingDTO addRating(int reviewerating, Date created, Long movieid, Long reviewerid){
        Reviewer reviewer=reviewerRepository.getOne(reviewerid);
        Rating rating=new Rating();
        rating.setReviewerating(reviewerating);
        rating.setCrated(created);
        Movie movie=movieRepository.getOne(movieid);
        rating.setMovie(movie);
        rating.setReviewer(reviewer);
        rating.setMovieid(movieid);
        rating.setReviewerid(reviewerid);
        ratingRepository.save(rating);
        return RatingDTO.mapEntityToDto(rating);
    }

    public RatingDTO updateRating(Rating rating,  Long reviewerid ){

        Reviewer reviewer=reviewerRepository.getOne(reviewerid);
        for(Rating rating1: reviewer.getRatingList()){
            if(rating1.getId().equals(rating.getId())){
                reviewer.getRatingList().remove(rating1);
            }
        }
        ratingRepository.save(rating);
        reviewer.getRatingList().add(rating);
        return RatingDTO.mapEntityToDto(rating);
    }

    public void deleteRating(Long id,Long reviewerid) {
        Reviewer reviewer=reviewerRepository.getOne(reviewerid);
        for(Rating rating1: reviewer.getRatingList()){
            if(rating1.getId().equals(id)){
                reviewer.getRatingList().remove(rating1);
            }
        }
        ratingRepository.deleteById(id);
    }

    public List<RatingDTO> getRating(Long reviewerid ){
        Reviewer reviewer=reviewerRepository.getOne(reviewerid);
        return RatingDTO.mapEntityToDtos(reviewer.getRatingList());
    }



}
