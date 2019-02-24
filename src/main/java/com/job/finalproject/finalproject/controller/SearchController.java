package com.job.finalproject.finalproject.controller;

import com.job.finalproject.finalproject.service.SearchService;
import com.job.finalproject.finalproject.service.dto.MovieDTO;
import com.job.finalproject.finalproject.service.dto.ReviewerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/search")
public class SearchController {


    private final SearchService searchService;
    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping ("/param={param}")
    public List<MovieDTO> getMovieByParam(@PathVariable String param){
        return searchService.findMovie(param,new PageRequest(0,100));
    }
    @GetMapping("/reviewer/param={param}")
    public List<ReviewerDTO> getReviewerByParam(@PathVariable String param){
        return searchService.findReviewer(param,new PageRequest(0,3));
    }
}
