package net.skideo.model;

import lombok.*;
import net.skideo.model.abstracts.AbstractComponentEntity;

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

    public Video(String link, User user) {
        setUser(user);
        this.videoLink = link;
    }

    public Video(String description,String link, User user) {
        this.description = description;
        this.videoLink = link;
        setUser(user);
    }

}
