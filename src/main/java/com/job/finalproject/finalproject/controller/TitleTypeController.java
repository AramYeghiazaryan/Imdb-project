package com.job.finalproject.finalproject.controller;

import com.job.finalproject.finalproject.persistance.entity.Movie;
import com.job.finalproject.finalproject.persistance.entity.TitleType;
import com.job.finalproject.finalproject.service.TitleTypeService;
import com.job.finalproject.finalproject.service.dto.MovieDTO;
import com.job.finalproject.finalproject.service.dto.TitleTypeDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/titletype")
public class TitleTypeController {
    private final TitleTypeService titleTypeService;

    public TitleTypeController(TitleTypeService titleTypeService) {
        this.titleTypeService = titleTypeService;
    }

    @GetMapping
    public List<TitleTypeDTO> getTitleType()  {
        return titleTypeService.getAllTitleType();
    }
    @GetMapping("/{id}")
    public TitleTypeDTO getByIdTitleType(@PathVariable("id") Long id)  {
        return titleTypeService.getTitleTypeById(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deletTitleType(@PathVariable("id") Long id){
        titleTypeService.deleteTitleType(id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping
    public TitleTypeDTO updateTitleType(@RequestBody TitleType genre)  {
        return titleTypeService.updateTitleType(genre);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public TitleTypeDTO addTitleType(@RequestParam String type)  {
        return titleTypeService.addTitleType(type);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{id}/movie")
    public MovieDTO addMovie(@RequestParam String code, @RequestParam Date releasdate, @RequestParam int runtime, @RequestParam String title, @RequestParam String url, @RequestParam BigDecimal imdbrating,@RequestParam int numvotes, @PathVariable("id") Long genreid) {
        return titleTypeService.addMovie(code,releasdate,runtime,title,url,imdbrating,numvotes,genreid);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}/movie")
    public MovieDTO updateMovie(@RequestBody Movie movie, @PathVariable("id") Long director_id){
        return titleTypeService.updateMovie(movie,director_id);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}/movie/{movieid}")
    public void deleteMovie(@PathVariable("movieid") Long movieid, @PathVariable("id") Long id){
        titleTypeService.deleteMovie(movieid,id);
    }
    @GetMapping("/{id}/movie")
    public List<MovieDTO> gattAllMovie(@PathVariable("id") Long id){
        return titleTypeService.getAllMovie(id);
    }
    @GetMapping("/{id}/movie/{movieid}")
    public MovieDTO getByIdMovie(@PathVariable("movieid") Long movieid, @PathVariable("id") Long id){
        return titleTypeService.getByIdMovie(movieid,id);
    }


}
