package com.job.finalproject.finalproject.service;



import com.job.finalproject.finalproject.persistance.entity.Director;
import com.job.finalproject.finalproject.persistance.entity.Movie;
import com.job.finalproject.finalproject.persistance.repository.DirectorRepository;
import com.job.finalproject.finalproject.persistance.repository.MovieRepository;
import com.job.finalproject.finalproject.service.dto.DirectorDTO;
import com.job.finalproject.finalproject.service.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final MovieRepository movieRepository;


    @Autowired
    public DirectorService(DirectorRepository directorRepository, MovieRepository movieRepository) {
        this.directorRepository = directorRepository;
        this.movieRepository = movieRepository;
    }

    public DirectorDTO addDirector(String wholename){
        Director director=new Director();
        director.setFullname(wholename);
        return  DirectorDTO.mapEntityToDto(directorRepository.save(director));

    }

    public DirectorDTO updateDirector(Director director){

        directorRepository.save(director);
        return DirectorDTO.mapEntityToDto(director);
    }

    public void  deleteDirector(Long id){
         directorRepository.deleteById(id);
    }

    public List<DirectorDTO> getAllDirector(){
        return DirectorDTO.mapEntityToDtos(directorRepository.findAll());
    }
    public  DirectorDTO getDirectorById(Long id){
        return DirectorDTO.mapEntityToDto(directorRepository.getOne(id));
    }

    public MovieDTO addMovie(String code, Date releasdate, int runtime, String title, String url, BigDecimal imbdrating,int numvotes, Long director_id){
        Director director=directorRepository.getOne(director_id);
        Movie movie=new Movie();
        movie.setCode(code);
        movie.setReleasdate(releasdate);
        movie.setRuntime(runtime);
        movie.setTitle(title);
        movie.setUrl(url);
        movie.setNumvotes(numvotes);
        movie.setImdbrating(imbdrating);
        director.getMovieList().add(movie);
        return MovieDTO.mapEntityToDto(movieRepository.save(movie));
    }

    public MovieDTO updateMovie(Movie movie, Long director_id){
        Director director=directorRepository.getOne(director_id);
        for(int i=0;i<director.getMovieList().size();i++){
            if(director.getMovieList().get(i).getId().equals(movie.getId())){
                director.getMovieList().remove(director.getMovieList().get(i));
            }
        }
        movieRepository.save(movie);
        director.getMovieList().add(movie);
        return MovieDTO.mapEntityToDto(movie);
    }

    public  void deleteMovie(Long id, Long director_id){
        Director director=directorRepository.getOne(director_id);
        for(int i=0;i<director.getMovieList().size();i++){
            if(director.getMovieList().get(i).getId().equals(id)){
                director.getMovieList().remove(director.getMovieList().get(i));
            }
        }
        movieRepository.deleteById(id);
    }

    public List<MovieDTO> getAllMovie(Long directorid){
        Director director=directorRepository.getOne(directorid);
        return MovieDTO.mapEntityToDtos(director.getMovieList());
    }

    public MovieDTO getByIdMovie(Long id, Long directorid){
        Director director=directorRepository.getOne(directorid);
        for(int i=0;i<director.getMovieList().size();i++){
            if(director.getMovieList().get(i).getId().equals(id)){
                return MovieDTO.mapEntityToDto(director.getMovieList().get(i));
            }
        }
      return null;
    }


}
