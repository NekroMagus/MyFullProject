package com.football.manager.domain;

import com.football.manager.domain.role.RoleInFootball;
import com.football.manager.domain.role.RoleOnTheSite;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "role_on_the_site")
    @Enumerated(value = EnumType.STRING)
    private RoleOnTheSite roleOnTheSite;

    @Column(name = "role_in_football")
    @Enumerated(value = EnumType.STRING)
    private RoleInFootball roleInFootball;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

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

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = "";
        this.surname = "";
        this.telephoneNumber = "";
        this.address = "";
        this.roleOnTheSite = RoleOnTheSite.ANONYMOUS;
        this.roleInFootball = RoleInFootball.GK;
        this.dateOfBirth = new Date(2000, 01, 01);
        this.dateOfRegistration = new Timestamp(new Date().getTime());
    }

    public User(String login, String password, String email, String name, String surname, String telephoneNumber, String address, RoleInFootball roleInFootball, Date dateOfBirth, String socialNetwork) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.roleOnTheSite = RoleOnTheSite.ANONYMOUS;
        this.roleInFootball = roleInFootball;
        this.dateOfBirth = dateOfBirth;
        this.socialNetwork = socialNetwork;
        this.dateOfRegistration = new Timestamp(new Date().getTime());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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
