package com.job.finalproject.finalproject.service.dto;

import com.job.finalproject.finalproject.persistance.entity.TitleType;

import java.util.ArrayList;
import java.util.List;

public class TitleTypeDTO {

    private Long id;
    private String type;

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

    public  static  TitleTypeDTO mapEntityToDto(TitleType titleType){
        TitleTypeDTO titleTypeDTO=new TitleTypeDTO();
        titleTypeDTO.setId(titleType.getId());
        titleTypeDTO.setType(titleType.getType());

        return titleTypeDTO;
    }
    public static List<TitleTypeDTO> mapEntityToDtos(List<TitleType> addressList){
        if(addressList==null){
            return  null;
        }
        List<TitleTypeDTO> addressDTOList=new ArrayList<>();
        for(TitleType address : addressList){
            addressDTOList.add(mapEntityToDto(address));
        }
        return addressDTOList;
    }
}
