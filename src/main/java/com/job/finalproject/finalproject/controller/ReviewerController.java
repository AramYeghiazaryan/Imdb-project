package com.job.finalproject.finalproject.controller;


import com.job.finalproject.finalproject.persistance.entity.Rating;
import com.job.finalproject.finalproject.persistance.entity.Reviewer;
import com.job.finalproject.finalproject.service.ReviewerService;
import com.job.finalproject.finalproject.service.dto.RatingDTO;
import com.job.finalproject.finalproject.service.dto.ReviewerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/reviewer")
public class ReviewerController {

    private final ReviewerService reviewerService;
    @Autowired
    public ReviewerController(ReviewerService reviewerService) {
        this.reviewerService = reviewerService;
    }
    @GetMapping
    public List<ReviewerDTO> getAllReviewer()  {
        return reviewerService.getAllReviewer();
    }
    @GetMapping("/{id}")
    public ReviewerDTO getByIdReviewer(@PathVariable("id") Long id)  {
        return reviewerService.getReviewerById(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteReviewer(@PathVariable("id") Long id){
        reviewerService.deletReviewer(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping
    public ReviewerDTO updateReviewer(@RequestBody Reviewer reviewer)  {
        return reviewerService.updateReviewer(reviewer);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ReviewerDTO addReviewer(@RequestParam String username)  {
        return reviewerService.addReviewer(username);
    }

    @GetMapping("/{id}/rating")
    public List<RatingDTO> getRating(@PathVariable("id") Long id)  {
        return reviewerService.getRating(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}/rating/{ratingid}")
    public void deleteRating(@PathVariable("id") Long id, @PathVariable("ratingid") Long directorid){
        reviewerService.deleteRating(directorid,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}/rating")
    public RatingDTO updateRating(@RequestBody Rating director, @PathVariable("id") Long id)  {
        return reviewerService.updateRating(director,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{id}/rating")
    public RatingDTO addRating(@RequestParam int reviewerating,@RequestParam Date created,@RequestParam  Long movieid,@PathVariable("id") Long reviewerid)  {
        return reviewerService.addRating(reviewerating,created,movieid,reviewerid);
    }

}
