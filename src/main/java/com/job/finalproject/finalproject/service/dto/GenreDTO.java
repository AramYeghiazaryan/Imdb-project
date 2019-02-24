package com.job.finalproject.finalproject.service.dto;

import com.job.finalproject.finalproject.persistance.entity.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenreDTO  {
    private Long id;
    private  String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public  static  GenreDTO mapEntityToDto(Genre genre){
        GenreDTO genreDTO=new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setType(genre.getType());

        return genreDTO;
    }
    public static List<GenreDTO> mapEntityToDtos(List<Genre> addressList){
        if(addressList==null){
            return  null;
        }
        List<GenreDTO> addressDTOList=new ArrayList<>();
        for(Genre address : addressList){
            addressDTOList.add(mapEntityToDto(address));
        }
        return addressDTOList;
    }
}
