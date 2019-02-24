package com.job.finalproject.finalproject.controller;


import com.job.finalproject.finalproject.persistance.entity.Movie;
import com.job.finalproject.finalproject.persistance.entity.Rating;
import com.job.finalproject.finalproject.persistance.entity.Reviewer;
import com.job.finalproject.finalproject.service.RatingService;
import com.job.finalproject.finalproject.service.ReviewerService;
import com.job.finalproject.finalproject.service.dto.MovieDTO;
import com.job.finalproject.finalproject.service.dto.RatingDTO;
import com.job.finalproject.finalproject.service.dto.ReviewerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private  final RatingService ratingService;
    private final ReviewerService reviewerService;
    @Autowired
    public RatingController(RatingService ratingService, ReviewerService reviewerService) {
        this.ratingService = ratingService;
        this.reviewerService=reviewerService;
    }

    @GetMapping
    public List<RatingDTO> getAllRating()  {
        return ratingService.getAllRating();
    }
    @GetMapping("/{id}")
    public RatingDTO getByIdRating(@PathVariable("id") Long id)  {
        return ratingService.getRatingById(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable("id") Long id){
        ratingService.deleteRating(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping
    public RatingDTO updateRating(@RequestBody Rating rating)  {
        return ratingService.updateRating(rating);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public RatingDTO addRating(@RequestParam int reviewerating,@RequestParam Date created, @RequestParam Long movieid,@RequestParam Long reviewerid)  {
        return ratingService.addRating(reviewerating,created,movieid,reviewerid);
    }

    @GetMapping("{id}/movie")
    public MovieDTO getAllMovie(@PathVariable("id") Long id)  {
        return ratingService.getMovie(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}/movie/{movieid}")
    public void deleteMovie(@PathVariable("id") Long id, @PathVariable("movieid") Long movieid){
        ratingService.deleteMovie(id, movieid);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}/movie")
    public MovieDTO updateMovie(@RequestBody Movie Movie, @PathVariable("id") Long id)  {
        return ratingService.updateMovie(Movie, id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("{id}/movie")
    public MovieDTO addMovie(@RequestParam String code, @RequestParam Date releasdate, @RequestParam int runtime, @RequestParam String title, @RequestParam String url, @RequestParam BigDecimal imdbrating,@RequestParam int numvotes, @PathVariable("id")  Long id)  {
        return ratingService.addMovie(code,releasdate,runtime,title,url,imdbrating,numvotes,id);
    }


    @GetMapping("/{id}/reviewer")
    public ReviewerDTO getAllReviewer(@PathVariable("id") Long id)  {
        return ratingService.getReviewer(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}/reviewer/{reviewerid}")
    public void deleteReviewer(@PathVariable("id") Long id, @PathVariable("reviewerid") Long directorid){
        ratingService.deleteReviewer(directorid,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}/reviewer")
    public ReviewerDTO updateReviewer(@RequestBody Reviewer director, @PathVariable("id") Long id)  {
        return ratingService.updateReviewer(director,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{id}/reviewer")
    public ReviewerDTO addReviewer(@RequestParam String username, @PathVariable("id") Long ratingid)  {
        return ratingService.addReviewer(username,ratingid);
    }

  /*  @GetMapping("/reviewer")
    public List<ReviewerDTO> getAllReviewerByGrade(){
        return ratingService.findReviewerByGrade();
    }*/

}
