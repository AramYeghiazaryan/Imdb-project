package com.job.finalproject.finalproject.service.dto;

import com.job.finalproject.finalproject.persistance.entity.Director;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DirectorDTO {
    private Long id;

    private String fullname;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  static  DirectorDTO mapEntityToDto(Director director){
        DirectorDTO directorDTO=new DirectorDTO();
        directorDTO.setId(director.getId());
        directorDTO.setFullname(director.getFullname());

        return directorDTO;
    }
    public static List<DirectorDTO> mapEntityToDtos(List<Director> addressList){
        if(addressList==null){
            return  null;
        }
        List<DirectorDTO> addressDTOList=new ArrayList<>();
        for(Director address : addressList){
            addressDTOList.add(mapEntityToDto(address));
        }
        return addressDTOList;
    }


}
