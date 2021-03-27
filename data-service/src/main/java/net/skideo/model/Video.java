package net.skideo.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"likes"})
@NoArgsConstructor
@Entity
@Table(name = "video")
public class Video extends AbstractComponentEntity {

    private String videoLink;
    private String description;
    private float rating;



    public Video(String link, Info info) {
        setInfo(info);
        this.videoLink = link;
    }

    public Video(String description,String link, Info info) {
        this.description = description;
        this.videoLink = link;
        setInfo(info);
    }

}
