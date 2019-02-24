package com.job.finalproject.finalproject.controller;


import com.job.finalproject.finalproject.persistance.entity.Genre;
import com.job.finalproject.finalproject.persistance.entity.Movie;
import com.job.finalproject.finalproject.service.GenreService;
import com.job.finalproject.finalproject.service.dto.GenreDTO;
import com.job.finalproject.finalproject.service.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {
    private final GenreService genreService;
    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreDTO> getGenre()  {
        return genreService.getAllGenre();
    }
    @GetMapping("/{id}")
    public GenreDTO getByIdGenre(@PathVariable("id") Long id)  {
        return genreService.getGenreById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") Long id){
        genreService.deleteGenre(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping
    public GenreDTO updateGenre(@RequestBody Genre genre)  {
        return genreService.updateGenre(genre);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public GenreDTO addGenre(@RequestParam String type)  {
        return genreService.addGenre(type);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{id}/movie")
    public MovieDTO addMovie(@RequestParam String code,  @RequestParam Date releasdate, @RequestParam int runtime, @RequestParam String title, @RequestParam String url,  @RequestParam BigDecimal imdbrating,@RequestParam int numvotes, @PathVariable("id") Long genreid) {
        return genreService.addMovie(code,releasdate,runtime,title,url,imdbrating,numvotes,genreid);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}/movie")
    public MovieDTO updateMovie(@RequestBody Movie movie, @PathVariable("id") Long director_id){
        return genreService.updateMovie(movie,director_id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}/movie/{movieid}")
    public void deleteMovie(@PathVariable("movieid") Long movieid, @PathVariable("id") Long id){
        genreService.deleteMovie(movieid,id);
    }
    @GetMapping("/{id}/movie")
    public List<MovieDTO> gattAllMovie(@PathVariable("id") Long id){
        return genreService.getAllMovie(id);
    }
    @GetMapping("/{id}/movie/{movieid}")
    public MovieDTO getByIdMovie(@PathVariable("movieid") Long movieid, @PathVariable("id") Long id){
        return genreService.getByIdMovie(movieid,id);
    }
}
