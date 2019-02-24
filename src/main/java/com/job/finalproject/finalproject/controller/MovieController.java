package com.job.finalproject.finalproject.controller;


import com.job.finalproject.finalproject.persistance.entity.*;
import com.job.finalproject.finalproject.service.DirectorService;
import com.job.finalproject.finalproject.service.GenreService;
import com.job.finalproject.finalproject.service.MovieService;
import com.job.finalproject.finalproject.service.RatingService;
import com.job.finalproject.finalproject.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;
    private final DirectorService directorService;
    private final GenreService genreService;
    private final RatingService ratingService;

    @Autowired
    public MovieController(MovieService movieService,DirectorService directorService,GenreService genreService,RatingService ratingService) {
        this.movieService = movieService;
        this.directorService=directorService;
        this.genreService=genreService;
        this.ratingService=ratingService;
    }

    @GetMapping
    public List<MovieDTO> getAllMovie()  {
        return movieService.getAllMovie();
    }
    @GetMapping("/{id}")
    public MovieDTO getByIdDirector(@PathVariable("id") Long id)  {
        return movieService.getByIdMovie(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") Long id){
        movieService.deleteMovie(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping
    public MovieDTO updateMovie(@RequestBody Movie Movie)  {
        return movieService.updateMovie(Movie);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public MovieDTO addMovie(@RequestParam String code,  @RequestParam Date releasdate, @RequestParam int runtime, @RequestParam String title, @RequestParam String url, @RequestParam BigDecimal imdbrating,@RequestParam int numvotes)  {
        return movieService.addMovie(code,releasdate,runtime,title,url,imdbrating,numvotes);
    }

    @GetMapping("/{id}/director")
    public List<DirectorDTO> getAllDirector(@PathVariable("id") Long id)  {
        return movieService.getAllDirector(id);
    }
    @GetMapping("/{id}/director/{directorid}")
    public DirectorDTO getByIdDirector(@PathVariable("id") Long id, @PathVariable("directorid") Long directorid)  {
        return movieService.getDirectorById(directorid,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}/director/{direcotrid}")
    public void deletDirector(@PathVariable("id") Long id, @PathVariable("directorid") Long directorid){
        movieService.deleteDirector(directorid,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}/director")
    public DirectorDTO updateDirector(@RequestBody Director director,@PathVariable("id") Long id)  {
        return movieService.updateDirector(director,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{id}/director")
    public DirectorDTO addDirector(@RequestParam String wholename,@PathVariable("id") Long id)  {
        return movieService.addDirector(wholename,id);
    }


  /*  @GetMapping("/name={name}")
    public List<MovieDTO> getMovieByName(@PathVariable("name") String name){
        return movieService.getMovieByName(name,new PageRequest(0,30,Sort.Direction.ASC));
    }
    @GetMapping("/director/name={name}")
    public List<MovieDTO> getMovieByDirectorName(@PathVariable("name") String name){
        return movieService.findAllMovieByDirectorName(name,new PageRequest(0,20,Sort.Direction.ASC));
    }
    @GetMapping("/genre/type={type}")
    public List<MovieDTO> getMovieByGenreType(@PathVariable("type") String type){
        return movieService.findAllMovieByGenreType(type,new PageRequest(0,20));
    }
    @GetMapping("/rating/grade")
    public List<MovieDTO> getAllMovieByGrade(){
        return movieService.findAllMovieByGrade();
    }*/

    @GetMapping("/{id}/genre")
    public List<GenreDTO> getAllGenre(@PathVariable("id") Long id)  {
        return movieService.getAllGenre(id);
    }
    @GetMapping("/{id}/genre/{genreid}")
    public GenreDTO getByIdGenre(@PathVariable("id") Long id, @PathVariable("genreid") Long directorid)  {
        return movieService.getGenreById(directorid,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}/genre/{genreid}")
    public void deleteGenre(@PathVariable("id") Long id, @PathVariable("genreid") Long directorid){
        movieService.deleteGenre(directorid,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}/genre")
    public GenreDTO updateGenre(@RequestBody Genre genre,@PathVariable("id") Long id)  {
        return movieService.updateGenre(genre,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{id}/genre")
    public GenreDTO addGenre(@RequestParam String type,@PathVariable("id") Long id)  {
        return movieService.addGenre(type,id);
    }

    @GetMapping("/{id}/titletype")
    public List<TitleTypeDTO> getAllTitleType(@PathVariable("id") Long id)  {
        return movieService.getAllTitleType(id);
    }
    @GetMapping("/{id}/titletype/{titletypeid}")
    public TitleTypeDTO getByIdTitleType(@PathVariable("id") Long id, @PathVariable("titletypeid") Long directorid)  {
        return movieService.getTitleTypeById(directorid,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}/titletype/{titletypeid}")
    public void deleteTitleType(@PathVariable("id") Long id, @PathVariable("titletypeid") Long directorid){
        movieService.deleteTitleType(directorid,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}/titletype")
    public TitleTypeDTO updateTitleType(@RequestBody TitleType genre, @PathVariable("id") Long id)  {
        return movieService.updateTitleType(genre,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{id}/titletype")
    public TitleTypeDTO addTitleType(@RequestParam String type,@PathVariable("id") Long id)  {
        return movieService.addTitleType(type,id);
    }

    @GetMapping("/{id}/rating")
    public List<RatingDTO> getAllRating(@PathVariable("id") Long id)  {
        return movieService.getAllRating(id);
    }
    @GetMapping("/{id}/rating/{ratingid}")
    public RatingDTO getByIdRating(@PathVariable("id") Long id, @PathVariable("ratingid") Long directorid)  {
        return movieService.getRatingById(directorid,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}/rating/{ratingid}")
    public void deleteRating(@PathVariable("id") Long id, @PathVariable("ratingid") Long directorid){
        movieService.deleteRating(directorid,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}/rating")
    public RatingDTO updateRating(@RequestBody Rating rating, @PathVariable("id") Long id)  {
        return movieService.updateRating(rating,id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{id}/rating")
    public RatingDTO addRating(@RequestParam int reviewerating,@RequestParam Date created, @RequestParam  Long reviewerid,@PathVariable("id") Long movieid)  {
        return movieService.addRating(reviewerating,created,reviewerid,movieid);
    }



}
