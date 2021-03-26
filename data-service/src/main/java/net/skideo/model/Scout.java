package net.skideo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "scout")
public class Scout extends BaseEntity {

    private String region;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Info info;

    @ManyToOne
    private Club club;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> favoriteUsers = new LinkedHashSet<>();

    public Scout(Info info) {
        this.info = info;
    }

}
