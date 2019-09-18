package com.football.manager.model;

import javax.persistence.*;

/**
 * Simple JavaBean object that represents a Photo files of {@link com.football.manager.model.User}.
 *
 * @author Igor Fliginskikh
 * @version 1.0
 */

@Entity
@Table(name = "user_photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User userPhoto;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name="description")
    private String description;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="content", nullable=false)
    private byte[] content;

    public Photo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(User userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
