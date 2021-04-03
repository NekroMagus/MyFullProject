package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.AbstractUserEntity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="user_academy")
public class Academy extends AbstractUserEntity {

    private int numberPlayers;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Player> players = new LinkedList<>();

    public Academy(User user, int numberPlayers) {
        setUser(user);
        this.numberPlayers = numberPlayers;
    }

}
