package com.job.finalproject.finalproject.service;

import com.job.finalproject.finalproject.persistance.entity.*;
import com.job.finalproject.finalproject.persistance.repository.*;
import com.job.finalproject.finalproject.service.dto.MovieDTO;
import com.job.finalproject.finalproject.service.dto.ReviewerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ReviewerRepository reviewerRepository;


    public List<MovieDTO>  findMovie(String category, PageRequest pageRequest) {
        List<MovieDTO> movieDTOS = new ArrayList<>();
        if (category.contains("[a-zA-Z]+") == false && category.length() > 2) {
            if (movieRepository.findByTitleContaining(category, pageRequest) != null) {
                movieDTOS.addAll(MovieDTO.mapEntityToDtos(movieRepository.findByTitleContaining(category, pageRequest)));
            }
            if (directorRepository.findDirectorByFullnameContaining(category, pageRequest) != null) {
                List<Director> directorList = directorRepository.findDirectorByFullnameContaining(category, pageRequest);
                List<BigInteger> movieId = new ArrayList<>();
                for (Director director : directorList) {
                    movieId.addAll(movieRepository.findMovieIdByDirectorFullName(director.getFullname(), pageRequest));
                }
                for (BigInteger id : movieId) {
                    movieDTOS.addAll(MovieDTO.mapEntityToDtos(movieRepository.getMovieById(id.longValue(), pageRequest)));
                }
            }
            if (genreRepository.getByType(category, pageRequest) != null) {
                List<Genre> genreList = genreRepository.getByType(category, pageRequest);
                List<BigInteger> moviId = new ArrayList<>();
                for (Genre genre : genreList) {
                    moviId.addAll(movieRepository.findMovieIdByGenreType(genre.getType(), pageRequest));
                }
                for (BigInteger id : moviId) {
                    movieDTOS.addAll(MovieDTO.mapEntityToDtos(movieRepository.getMovieById(id.longValue(), pageRequest)));
                }
            }
        }
        else {
            int grade = Integer.parseInt(category);
            movieDTOS.addAll(MovieDTO.mapEntityToDtos(movieRepository.findMovieTitleByGrade(grade, pageRequest)));

        }
        return movieDTOS;
    }
    public List<ReviewerDTO> findReviewer(String title,PageRequest pageRequest){
        //TODO HOW CREATE TABLE WITH QUERY
        // ratingRepository.createMovieRatingTable();
        List<BigInteger> reviewerList=ratingRepository.getMovieIdByTitle(title,pageRequest);
        List<ReviewerDTO> reviewerDTOS=new ArrayList<>();
        for(BigInteger id:reviewerList){
            reviewerDTOS.addAll(ReviewerDTO.mapEntityToDtos(reviewerRepository.getReviewerById(id.longValue(),pageRequest)));
        }
        return reviewerDTOS;
    }

}
