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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Info info;

    @OneToMany(mappedBy = "video")
    private Set<Like> likes = new HashSet<>();

    public Video(String link, Info info) {
        this.videoLink = link;
        this.info=info;
    }

}
