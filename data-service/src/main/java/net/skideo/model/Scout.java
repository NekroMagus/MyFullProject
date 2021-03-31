package net.skideo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.AbstractInfoEntity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_scout")
public class Scout extends AbstractInfoEntity {

    private String region;

    @ManyToOne
    private Club club;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Player> favoritePlayers = new LinkedHashSet<>();

    public Scout(Info info) {
        setInfo(info);
    }

}
