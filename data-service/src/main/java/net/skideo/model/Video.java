package net.skideo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"likes"})
@NoArgsConstructor
@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String videoLink;
    private String description;

    @ManyToOne(optional = false)
    private Info info;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "video")
    private Set<Like> likes = new HashSet<>();

    public Video(String link, Info info) {
        this.videoLink = link;
        this.info=info;
    }

    public Video(String description,String link, Info info) {
        this.description = description;
        this.videoLink = link;
        this.info=info;
    }

}
