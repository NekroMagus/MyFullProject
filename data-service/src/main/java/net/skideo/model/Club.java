package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.AbstractUserEntity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_club")
public class Club extends AbstractUserEntity {

    private String logoLink;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Player> favoritePlayers = new LinkedHashSet<>();

    public Club(User user, String logoLink) {
        setUser(user);
        this.logoLink = logoLink;
    }

}
