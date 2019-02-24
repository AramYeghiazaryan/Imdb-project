package com.job.finalproject.finalproject.service.dto;

import com.job.finalproject.finalproject.persistance.entity.Genre;
import com.job.finalproject.finalproject.persistance.entity.Movie;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MovieDTO {
    private Long id;
    private String code;
    private Date releasdate;
    private int runtime;
    private String title;
    private String url;
    private BigDecimal imdbrating;
    private int numvotes;

    public BigDecimal getImdbrating() {
        return imdbrating;
    }

    public void setImdbrating(BigDecimal imdbrating) {
        this.imdbrating = imdbrating;
    }

    public int getNumvotes() {
        return numvotes;
    }

    public void setNumvotes(int numvotes) {
        this.numvotes = numvotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Date getReleasdate() {
        return releasdate;
    }

    public void setReleasdate(Date releasdate) {
        this.releasdate = releasdate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


     public  static  MovieDTO mapEntityToDto(Movie movie){
        MovieDTO movieDTO=new MovieDTO();
         movieDTO.setId(movie.getId());
        movieDTO.setCode(movie.getCode());
        movieDTO.setReleasdate(movie.getReleasdate());
        movieDTO.setRuntime(movie.getRuntime());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setUrl(movie.getUrl());
        movieDTO.setImdbrating(movie.getImdbrating());
        movieDTO.setNumvotes(movie.getNumvotes());

        return movieDTO;
    }
    public static List<MovieDTO> mapEntityToDtos(List<Movie> addressList){
        if(addressList==null){
            return  null;
        }
        List<MovieDTO> addressDTOList=new ArrayList<>();
        for(Movie address : addressList){
            addressDTOList.add(mapEntityToDto(address));
        }
        return addressDTOList;
    }
}
