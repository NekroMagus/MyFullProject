package data.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String videoLink;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Like> likes;
    @ManyToOne(targetEntity = User.class)
    private User user;

    public Video(String videoLink) {
        this.videoLink=videoLink;
    }

}
