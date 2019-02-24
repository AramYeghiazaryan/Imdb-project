package com.job.finalproject.finalproject.service.dto;

import com.job.finalproject.finalproject.persistance.entity.Reviewer;

import java.util.ArrayList;
import java.util.List;

public class ReviewerDTO {

    private Long id;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public  static  ReviewerDTO mapEntityToDto(Reviewer reviewer){
        ReviewerDTO reviewerDTO=new ReviewerDTO();
        reviewerDTO.setId(reviewer.getId());
        reviewerDTO.setUsername(reviewer.getUsername());

        return reviewerDTO;
    }
    public static List<ReviewerDTO> mapEntityToDtos(List<Reviewer> addressList){
        if(addressList==null){
            return  null;
        }
        List<ReviewerDTO> addressDTOList=new ArrayList<>();
        for(Reviewer address : addressList){
            addressDTOList.add(mapEntityToDto(address));
        }
        return addressDTOList;
    }

}
