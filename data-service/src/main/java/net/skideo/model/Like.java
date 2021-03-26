package net.skideo.model;

import net.skideo.model.enums.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "likes")
public class Like extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Rating rating;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id")
    private Info info;

    public Like(Rating rating,Info info) {
        this.rating=rating;
        this.info=info;
    }


}
