package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "club")
public class Club extends AbstractInfoEntity {

    private String logoLink;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> favoriteUsers = new LinkedHashSet<>();

    public Club(Info info,String logoLink) {
        setInfo(info);
        this.logoLink = logoLink;
    }

    public Club(String login,String password,String titleClub) {
        getInfo().setLogin(login);
        getInfo().setPassword(password);
        getInfo().setName(titleClub);
    }

}
