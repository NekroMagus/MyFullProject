package net.skideo.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"likes"})
@NoArgsConstructor
@Entity
@Table(name = "video")
public class Video extends BaseEntity {

    private String videoLink;
    private String description;
    private float rating;

    @ManyToOne(optional = false)
    private Info info;

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private Set<Like> likes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Comment> comments=new LinkedList<>();

    public Video(String link, Info info) {
        this.info=info;
        this.videoLink = link;
    }

    public Video(String description,String link, Info info) {
        this.description = description;
        this.videoLink = link;
        this.info=info;
    }

}
