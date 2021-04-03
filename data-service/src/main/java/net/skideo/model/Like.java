package net.skideo.model;

import net.skideo.model.abstracts.BaseEntity;
import net.skideo.model.enums.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class Like extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Rating rating;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id")
    private User user;

    public Like(Rating rating, User user) {
        this.rating=rating;
        this.user = user;
    }


}
