package com.job.finalproject.finalproject.service;

import com.job.finalproject.finalproject.persistance.entity.*;
import com.job.finalproject.finalproject.persistance.repository.*;
import com.job.finalproject.finalproject.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final TitleTypeRepository titleTypeRepository;
    private final DirectorRepository directorRepository;
    private final RatingRepository ratingRepository;
    private final ReviewerRepository reviewerRepository;


    @Autowired
    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, TitleTypeRepository titleTypeRepository, DirectorRepository directorRepository, RatingRepository ratingRepository, ReviewerRepository reviewerRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.titleTypeRepository = titleTypeRepository;
        this.directorRepository = directorRepository;
        this.ratingRepository = ratingRepository;
        this.reviewerRepository = reviewerRepository;
    }

    public MovieDTO addMovie(String code,  Date releasdate, int runtime, String title, String url, BigDecimal imbdrating,int numvotes) {
        Movie movie = new Movie();
        movie.setCode(code);
        movie.setReleasdate(releasdate);
        movie.setRuntime(runtime);
//        movieRepository.getMovieById()
        movie.setTitle(title);
        movie.setUrl(url);

        movie.setImdbrating(imbdrating);
        movie.setNumvotes(numvotes);
        return MovieDTO.mapEntityToDto(movieRepository.save(movie));
    }

    public MovieDTO updateMovie(Movie movie) {
        movieRepository.save(movie);
        return MovieDTO.mapEntityToDto(movie);
    }

    public void deleteMovie(Long id) {

        movieRepository.deleteById(id);
    }

    public List<MovieDTO> getAllMovie() {
        return MovieDTO.mapEntityToDtos(movieRepository.findAll());
    }



    public MovieDTO getByIdMovie(Long id) {
        return MovieDTO.mapEntityToDto(movieRepository.getOne(id));
    }
    public List<MovieDTO> getMovieByName(String name, PageRequest pageRequest){
        return MovieDTO.mapEntityToDtos(movieRepository.findByTitleContaining(name,pageRequest));
    }

    public DirectorDTO addDirector(String wholename, Long movieid) {
        Director director = new Director();
        director.setFullname(wholename);
        Movie movie = movieRepository.getOne(movieid);
        movie.getDirectorList().add(director);
        return DirectorDTO.mapEntityToDto(directorRepository.save(director));
    }

    public DirectorDTO updateDirector(Director director, Long moieid) {
        Movie movie = movieRepository.getOne(moieid);
        for (int i = 0; i < movie.getDirectorList().size(); i++) {
            if (movie.getDirectorList().get(i).getId().equals( director.getId())) {
                movie.getDirectorList().remove(movie.getDirectorList().get(i));
            }
        }

        directorRepository.save(director);
        movie.getDirectorList().add(director);
        return DirectorDTO.mapEntityToDto(director);
    }

    public void deleteDirector(Long id, Long movieid) {
        Movie movie = movieRepository.getOne(movieid);
        for (int i = 0; i < movie.getDirectorList().size(); i++) {
            if (movie.getDirectorList().get(i).getId().equals( id)) {
                movie.getDirectorList().remove(movie.getDirectorList().get(i));
            }
        }
        directorRepository.deleteById(id);
    }

    public List<DirectorDTO> getAllDirector(Long movieid) {
        Movie movie = movieRepository.getOne(movieid);
        return DirectorDTO.mapEntityToDtos(movie.getDirectorList());
    }

    public DirectorDTO getDirectorById(Long id, Long movieid) {
        Director director = null;
        Movie movie = movieRepository.getOne(movieid);
        for (int i = 0; i < movie.getDirectorList().size(); i++) {
            if (movie.getDirectorList().get(i).getId() .equals( id)) {
                director.setFullname(movie.getDirectorList().get(i).getFullname());
                director.setId(movie.getDirectorList().get(i).getId());
                director.setMovieList(movie.getDirectorList().get(i).getMovieList());
                break;
            }
        }
        return DirectorDTO.mapEntityToDto(director);
    }

    public GenreDTO addGenre(String type, Long movieid) {
        Movie movie = movieRepository.getOne(movieid);
        Genre genre = new Genre();
        genre.setType(type);
        movie.getGenres().add(genre);
        return GenreDTO.mapEntityToDto(genreRepository.save(genre));

    }

    public GenreDTO updateGenre(Genre genre, Long movieid) {
        Movie movie = movieRepository.getOne(movieid);
        for (int i = 0; i < movie.getGenres().size(); i++) {
            if (movie.getGenres().get(i).getId().equals(genre.getId())) {
                movie.getGenres().remove(movie.getGenres().get(i));
            }
        }

        genreRepository.save(genre);
        movie.getGenres().add(genre);
        return GenreDTO.mapEntityToDto(genre);
    }

    public void deleteGenre(Long id, Long movieid) {

        Movie movie = movieRepository.getOne(movieid);
        for (int i = 0; i < movie.getGenres().size(); i++) {
            if (movie.getGenres().get(i).getId().equals(id)) {
                movie.getGenres().remove(movie.getGenres().get(i));
            }
        }

        genreRepository.deleteById(id);
    }

    public List<GenreDTO> getAllGenre(Long movieid) {
        Movie movie = movieRepository.getOne(movieid);


        return GenreDTO.mapEntityToDtos(movie.getGenres());
    }

    public GenreDTO getGenreById(Long id, Long movieid) {
        Movie movie = movieRepository.getOne(movieid);
        for (int i = 0; i < movie.getGenres().size(); i++) {
            if (movie.getGenres().get(i).getId().equals(id)) {
                return GenreDTO.mapEntityToDto(movie.getGenres().get(i));
            }
        }

        return null;
    }

    public TitleTypeDTO addTitleType(String type, Long movieid) {
        Movie movie = movieRepository.getOne(movieid);
        TitleType titleType = new TitleType();
        titleType.setType(type);
        movie.getTitleTypeList().add(titleType);
        return TitleTypeDTO.mapEntityToDto(titleTypeRepository.save(titleType));

    }

    public TitleTypeDTO updateTitleType(TitleType titleType, Long movieid) {
        Movie movie = movieRepository.getOne(movieid);
        for (int i = 0; i < movie.getTitleTypeList().size(); i++) {
            if (movie.getTitleTypeList().get(i).getId().equals(titleType.getId())) {
                movie.getTitleTypeList().remove(movie.getTitleTypeList().get(i));
            }
        }

        titleTypeRepository.save(titleType);
        movie.getTitleTypeList().add(titleType);
        return TitleTypeDTO.mapEntityToDto(titleType);
    }

    public void deleteTitleType(Long id,Long movieid) {
        Movie movie=movieRepository.getOne(movieid);
        for(int i=0;i<movie.getTitleTypeList().size();i++){
            if(movie.getTitleTypeList().get(i).getId().equals(id)){
                movie.getTitleTypeList().remove(movie.getTitleTypeList().get(i));
            }
        }

        titleTypeRepository.deleteById(id);
    }

    public List<TitleTypeDTO> getAllTitleType(Long movieid){
        Movie movie=movieRepository.getOne(movieid);
        return TitleTypeDTO.mapEntityToDtos(movie.getTitleTypeList());
    }
    public  TitleTypeDTO getTitleTypeById(Long id, Long movieid){
        Movie movie=movieRepository.getOne(movieid);
        for(int i=0;i<movie.getTitleTypeList().size();i++){
            if(movie.getTitleTypeList().get(i).getId().equals(id)){
                return TitleTypeDTO.mapEntityToDto(movie.getTitleTypeList().get(i));
            }
        }

        return null;
    }

    public RatingDTO addRating(int reviewerating,Date created,  Long reviewerid, Long movieid){
        Movie movie=movieRepository.getOne(movieid);
        Reviewer reviewer=reviewerRepository.getOne(reviewerid);
        Rating rating=new Rating();
        rating.setReviewerating(reviewerating);
        rating.setCrated(created);
        rating.setMovie(movie);
        rating.setReviewer(reviewer);
        rating.setMovieid(movieid);
        rating.setReviewerid(reviewerid);
        ratingRepository.save(rating);
        return RatingDTO.mapEntityToDto(rating);
    }

    public RatingDTO updateRating(Rating rating,Long movieid){
        Movie movie=movieRepository.getOne(movieid);
        for(int i=0;i<movie.getRatingList().size();i++){
            if(movie.getRatingList().get(i).getId().equals(rating.getId())){
                movie.getRatingList().remove(movie.getRatingList().get(i));
            }
        }
        ratingRepository.save(rating);
        movie.getRatingList().add(rating);
        return RatingDTO.mapEntityToDto(rating);
    }

    public void deleteRating(Long id, Long movieid){
        Movie movie=movieRepository.getOne(movieid);
        for(int i=0;i<movie.getRatingList().size();i++){
            if(movie.getRatingList().get(i).getId().equals(id)){
                movie.getRatingList().remove(movie.getRatingList().get(i));
            }
        }

        ratingRepository.deleteById(id);
    }

    public List<RatingDTO> getAllRating(Long movieid){
        Movie movie=movieRepository.getOne(movieid);
        return RatingDTO.mapEntityToDtos(movie.getRatingList());
    }

    public RatingDTO getRatingById(Long id , Long movieid){
        Movie movie=movieRepository.getOne(movieid);
        for(int i=0;i<movie.getRatingList().size();i++){
            if(movie.getRatingList().get(i).getId().equals(id)){
                return RatingDTO.mapEntityToDto(movie.getRatingList().get(i));
            }
        }
        return null;
    }


   /* public List<MovieDTO> findAllMovieByDirectorName(String name,PageRequest pageRequest){
        List<BigInteger> movieid=movieRepository.findMovieIdByDirectorFullName(name,pageRequest);
        List<MovieDTO> movieDTOS=new ArrayList<>();
        for(BigInteger id:movieid){
            movieDTOS.add(MovieDTO.mapEntityToDto(movieRepository.getOne(id.longValue())));
        }
        return movieDTOS;
    }

    public List<MovieDTO> findAllMovieByGenreType(String type,PageRequest pageRequest){
        List<BigInteger> movieid=movieRepository.findMovieIdByGenreType(type,pageRequest);
        List<MovieDTO> movieDTOS=new ArrayList<>();
        for(BigInteger id: movieid){
            movieDTOS.add(MovieDTO.mapEntityToDto(movieRepository.getOne(id.longValue())));
        }

        return movieDTOS;
    }
    public List<MovieDTO> findAllMovieByGrade(int grade,PageRequest pageRequest){
        List<Movie> title=movieRepository.findMovieTitleByGrade(grade,pageRequest);
        List<MovieDTO> movieDTOS=new ArrayList<>();
        for(Movie t: title){
            movieDTOS.addAll(MovieDTO.mapEntityToDtos(movieRepository.getMovieByTitle(t.getTitle(),pageRequest)));
        }

        return movieDTOS;
    }*/

}
