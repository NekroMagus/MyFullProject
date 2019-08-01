package com.football.manager.domain;

import javax.persistence.*;

@Entity
@Table(name= "user_video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @ManyToOne(optional = false,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", nullable = false)
    private User userVideo;

    public Video() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUserVideo() {
        return userVideo;
    }

    public void setUserVideo(User userVideo) {
        this.userVideo = userVideo;
    }
}
