package com.job.finalproject.finalproject.controller;


import com.job.finalproject.finalproject.persistance.entity.Director;
import com.job.finalproject.finalproject.persistance.entity.Movie;
import com.job.finalproject.finalproject.service.DirectorService;
import com.job.finalproject.finalproject.service.dto.DirectorDTO;
import com.job.finalproject.finalproject.service.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/director")
public class DirectorController {
    
    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public List<DirectorDTO> getDirector()  {
        return directorService.getAllDirector();
    }
    @GetMapping("/{id}")
    public DirectorDTO getByIdDirector(@PathVariable("id") Long id)  {
        return directorService.getDirectorById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteDirector(@PathVariable("id") Long id){
        directorService.deleteDirector(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping
    public DirectorDTO updateDirector(@RequestBody Director director)  {
        return directorService.updateDirector(director);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public DirectorDTO addDirector(@RequestParam String wholename)  {
        return directorService.addDirector(wholename);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{id}/movie")
    public MovieDTO addMovie(@RequestParam String code, @RequestParam Date releasdate, @RequestParam int runtime, @RequestParam String title, @RequestParam String url, @RequestParam BigDecimal imdbrating, @RequestParam int numvotes, @PathVariable("id") Long director_id){
        return directorService.addMovie(code,releasdate,runtime,title,url,imdbrating,numvotes,director_id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}/movie")
    public MovieDTO updateMovie(@RequestBody Movie movie, @PathVariable("id") Long director_id){
        return directorService.updateMovie(movie,director_id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}/movie/{movieid}")
    public void deleteMovie(@PathVariable("movieid") Long movieid, @PathVariable("id") Long id){
         directorService.deleteMovie(movieid,id);
    }

    @GetMapping("/{id}/movie")
    public List<MovieDTO> gattAllMovie(@PathVariable("id") Long id){
        return directorService.getAllMovie(id);
    }
    @GetMapping("/{id}/movie/{movieid}")
    public MovieDTO getByIdMovie(@PathVariable("movieid") Long movieid, @PathVariable("id") Long id){
        return directorService.getByIdMovie(movieid,id);
    }
}
