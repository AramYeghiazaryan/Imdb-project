package com.job.finalproject.finalproject.service.dto;

import com.job.finalproject.finalproject.persistance.entity.Rating;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RatingDTO {

    private Long id;
    private double reviewerating;
    private Date created;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getReviewerating() {
        return reviewerating;
    }

    public void setReviewerating(double reviewerating) {
        this.reviewerating = reviewerating;
    }

    public  static  RatingDTO mapEntityToDto(Rating rating){
        RatingDTO ratingDTO=new RatingDTO();
        ratingDTO.setId(rating.getId());
        ratingDTO.setReviewerating(rating.getReviewerating());
        ratingDTO.setCreated(rating.getCrated());

        return ratingDTO;
    }
    public static List<RatingDTO> mapEntityToDtos(List<Rating> addressList){
        if(addressList==null){
            return  null;
        }
        List<RatingDTO> addressDTOList=new ArrayList<>();
        for(Rating address : addressList){
            addressDTOList.add(mapEntityToDto(address));
        }
        return addressDTOList;
    }

}
