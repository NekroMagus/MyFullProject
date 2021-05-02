package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.AbstractUserEntity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="user_academy")
public class Academy extends AbstractUserEntity {

    private Integer numberPlayers;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Player> players = new LinkedHashSet<>();

    public Academy(User user, int numberPlayers) {
        setUser(user);
        this.numberPlayers = numberPlayers;
    }

}
