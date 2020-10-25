package data.service.model;

import data.service.model.enums.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="skideo_like")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(value=EnumType.STRING)
    private Rating rating;
    @ManyToOne(fetch = FetchType.LAZY)
    private Video video;
    @ManyToOne
    private User user;


}
