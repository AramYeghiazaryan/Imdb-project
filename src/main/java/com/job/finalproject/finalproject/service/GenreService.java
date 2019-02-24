package com.job.finalproject.finalproject.service;

import com.job.finalproject.finalproject.persistance.entity.Genre;
import com.job.finalproject.finalproject.persistance.entity.Movie;
import com.job.finalproject.finalproject.persistance.repository.GenreRepository;
import com.job.finalproject.finalproject.persistance.repository.MovieRepository;
import com.job.finalproject.finalproject.service.dto.GenreDTO;
import com.job.finalproject.finalproject.service.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;

@Autowired
    public GenreService(GenreRepository genreRepository, MovieRepository movieRepository) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
    }


    public GenreDTO addGenre(String type){
        Genre genre=new Genre();
        genre.setType(type);
        return GenreDTO.mapEntityToDto(genreRepository.save(genre));

    }

    public GenreDTO updateGenre(Genre genre){

        genreRepository.save(genre);
        return GenreDTO.mapEntityToDto(genre);
    }

    public void  deleteGenre(Long id){
        genreRepository.deleteById(id);
    }

    public List<GenreDTO> getAllGenre(){
        return GenreDTO.mapEntityToDtos(genreRepository.findAll());
    }
    public  GenreDTO getGenreById(Long id){
        return GenreDTO.mapEntityToDto(genreRepository.getOne(id));
    }

    public MovieDTO addMovie(String code,  Date releasdate, int runtime, String title, String url, BigDecimal imdbrating,int numvotes, Long genreid){
        Genre genre=genreRepository.getOne(genreid);
        Movie movie=new Movie();
        movie.setCode(code);
        movie.setReleasdate(releasdate);
        movie.setRuntime(runtime);
        movie.setTitle(title);
        movie.setUrl(url);
        movie.setImdbrating(imdbrating);
        movie.setNumvotes(numvotes);
        genre.getMovies().add(movie);
        return MovieDTO.mapEntityToDto(movieRepository.save(movie));
    }

    public MovieDTO updateMovie(Movie movie, Long genreid){
        Genre genre=genreRepository.getOne(genreid);
        for(int i = 0; i<genre.getMovies().size(); i++){
            if(genre.getMovies().get(i).getId().equals(movie.getId())){
                genre.getMovies().remove(genre.getMovies().get(i));
            }
        }
        movieRepository.save(movie);
        genre.getMovies().add(movie);
        return MovieDTO.mapEntityToDto(movie);
    }

    public  void deleteMovie(Long id, Long genreid){
        Genre genre=genreRepository.getOne(genreid);
        for(int i = 0; i<genre.getMovies().size(); i++){
            if(genre.getMovies().get(i).getId().equals(id)){
                genre.getMovies().remove(genre.getMovies().get(i));
            }
        }
        movieRepository.deleteById(id);
    }

    public List<MovieDTO> getAllMovie(Long genreid){
        Genre genre=genreRepository.getOne(genreid);
        return MovieDTO.mapEntityToDtos(genre.getMovies());
    }

    public MovieDTO getByIdMovie(Long id,Long genreid){
        Genre genre=genreRepository.getOne(genreid);
        for(int i = 0; i<genre.getMovies().size(); i++){
            if(genre.getMovies().get(i).getId().equals(id)){
                return MovieDTO.mapEntityToDto(genre.getMovies().get(i));
            }
        }

        return null;
    }

}
