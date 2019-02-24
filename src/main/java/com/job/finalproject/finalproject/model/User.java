package com.job.finalproject.finalproject.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "password")
    private String password;

    private String username;

    private String fullname;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles=new ArrayList<>();

    public User() {
    }

    public User(User user) {
        this.roles = user.getRoles();
        this.username = user.getUsername();
        this.fullname = user.getfullname();
        this.id = user.getId();
        this.password = user.getPassword();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getfullname() {
        return fullname;
    }

    public void setfullname(String fullname) {
        this.fullname = fullname;
    }

    public  List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
