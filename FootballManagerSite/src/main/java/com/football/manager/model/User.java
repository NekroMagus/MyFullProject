package com.football.manager.model;

import com.football.manager.model.role.RoleInFootball;
import com.football.manager.model.role.RoleOnTheSite;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Simple JavaBean domain object that represents a User.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "role_on_the_site")
    @Enumerated(value = EnumType.STRING)
    private RoleOnTheSite roleOnTheSite;

    @Column(name = "role_in_football")
    @Enumerated(value = EnumType.STRING)
    private RoleInFootball roleInFootball;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_registration")
    private Timestamp dateOfRegistration;

    @Column(name = "social_network")
    private String socialNetwork;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userPhoto")
    private List<Photo> photoList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userVideo")
    private List<Video> videoList = new ArrayList<>();

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.roleOnTheSite = RoleOnTheSite.ANONYMOUS;
        this.dateOfBirth = LocalDate.of(2000,1,1);
        this.dateOfRegistration = new Timestamp(new Date().getTime());
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public RoleOnTheSite getRoleOnTheSite() {
        return roleOnTheSite;
    }

    public void setRoleOnTheSite(RoleOnTheSite roleOnTheSite) {
        this.roleOnTheSite = roleOnTheSite;
    }

    public RoleInFootball getRoleInFootball() {
        return roleInFootball;
    }

    public void setRoleInFootball(RoleInFootball roleInFootball) {
        this.roleInFootball = roleInFootball;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Timestamp getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Timestamp dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

}
