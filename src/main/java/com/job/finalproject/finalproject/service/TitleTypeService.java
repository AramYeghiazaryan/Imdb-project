package com.job.finalproject.finalproject.service;

import com.job.finalproject.finalproject.persistance.entity.Movie;
import com.job.finalproject.finalproject.persistance.entity.TitleType;
import com.job.finalproject.finalproject.persistance.repository.MovieRepository;
import com.job.finalproject.finalproject.persistance.repository.TitleTypeRepository;
import com.job.finalproject.finalproject.service.dto.MovieDTO;
import com.job.finalproject.finalproject.service.dto.TitleTypeDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
public class TitleTypeService {

    private final TitleTypeRepository titleTypeRepository;
    private final MovieRepository movieRepository;

    public TitleTypeService(TitleTypeRepository titleTypeRepository, MovieRepository movieRepository) {
        this.titleTypeRepository = titleTypeRepository;
        this.movieRepository = movieRepository;
    }

    public TitleTypeDTO addTitleType(String type){
        TitleType titleType=new TitleType();
        titleType.setType(type);
        return TitleTypeDTO.mapEntityToDto(titleTypeRepository.save(titleType));

    }

    public TitleTypeDTO updateTitleType(TitleType titleType){

        titleTypeRepository.save(titleType);
        return TitleTypeDTO.mapEntityToDto(titleType);
    }

    public void  deleteTitleType(Long id){
        titleTypeRepository.deleteById(id);
    }

    public List<TitleTypeDTO> getAllTitleType(){
        return TitleTypeDTO.mapEntityToDtos(titleTypeRepository.findAll());
    }
    public  TitleTypeDTO getTitleTypeById(Long id){
        return TitleTypeDTO.mapEntityToDto(titleTypeRepository.getOne(id));
    }

    public MovieDTO addMovie(String code, Date releasdate, int runtime, String title, String url, BigDecimal imbdrating,int numvotes, Long titletypeid){
        TitleType titleType=titleTypeRepository.getOne(titletypeid);
        Movie movie=new Movie();
        movie.setCode(code);
        movie.setReleasdate(releasdate);
        movie.setRuntime(runtime);
        movie.setTitle(title);
        movie.setUrl(url);
        movie.setImdbrating(imbdrating);
        movie.setNumvotes(numvotes);
        titleType.getMovieList().add(movie);
        return MovieDTO.mapEntityToDto(movieRepository.save(movie));
    }

    public MovieDTO updateMovie(Movie movie, Long titletypeid){
        TitleType titleType=titleTypeRepository.getOne(titletypeid);
        for(int i=0;i<titleType.getMovieList().size();i++){
            if(titleType.getMovieList().get(i).getId().equals(movie.getId())){
                titleType.getMovieList().remove(titleType.getMovieList().get(i));
            }
        }
        movieRepository.save(movie);
        titleType.getMovieList().add(movie);
        return MovieDTO.mapEntityToDto(movie);
    }

    public  void deleteMovie(Long id, Long titletypeid){
        TitleType titleType=titleTypeRepository.getOne(titletypeid);
        for(int i=0;i<titleType.getMovieList().size();i++){
            if(titleType.getMovieList().get(i).getId().equals(id)){
                titleType.getMovieList().remove(titleType.getMovieList().get(i));
            }
        }
        movieRepository.deleteById(id);
    }

    public List<MovieDTO> getAllMovie(Long titletypeid){
        TitleType titleType=titleTypeRepository.getOne(titletypeid);
        return MovieDTO.mapEntityToDtos(titleType.getMovieList());
    }

    public MovieDTO getByIdMovie(Long id,Long titletypeid){
        TitleType titleType=titleTypeRepository.getOne(titletypeid);
        for(int i=0;i<titleType.getMovieList().size();i++){
            if(titleType.getMovieList().get(i).getId().equals(id)){
                return MovieDTO.mapEntityToDto(titleType.getMovieList().get(i));
            }
        }

        return null;
    }
}
