package com.job.finalproject.finalproject.persistance.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "reviewer")
public class Reviewer {
    @Id
    @Column(name = "id",nullable = false,unique = true)
    private Long id;
    private String username;


    @OneToMany(mappedBy = "reviewer",cascade = CascadeType.ALL)
    private List<Rating> ratingList=new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Reviewer(Reviewer reviewer){
        this.setId(reviewer.getId());
        this.setRatingList(reviewer.getRatingList());
        this.setUsername(reviewer.getUsername());
    }
    public Reviewer(){ }

    @Override
    public String toString() {
        return "Reviewer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reviewer reviewer = (Reviewer) o;
        return Objects.equals(id, reviewer.id) &&
                Objects.equals(username, reviewer.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username);
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public Long getId() {

        return id;
    }
}
