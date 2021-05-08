package net.skideo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.AbstractUserEntity;
import net.skideo.model.enums.Region;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_scout")
public class Scout extends AbstractUserEntity {

    private Region region;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Club club;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "scout_player", joinColumns = @JoinColumn(name = "scout_id"), inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Set<Player> favoritePlayers = new LinkedHashSet<>();

    public Scout(User user) {
        setUser(user);
    }

}
