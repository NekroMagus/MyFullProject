package net.skideo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.AbstractEntity;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import net.skideo.model.enums.ServiceRole;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(exclude = {"notifications"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User extends AbstractEntity {

    @Column(unique = true)
    private String login;
    @Column(unique = true)
    private String password;
    @Column(unique = true)
    private String email;
    private String name;
    private String surname;
    @OneToOne(cascade = CascadeType.MERGE)
    private City city;
    @Enumerated(value = EnumType.STRING)
    private ServiceRole serviceRole;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST},fetch = FetchType.LAZY)
    private Set<Notification> notifications = new TreeSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Player> player = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Scout> scout = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Academy> academy = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Club> club = new ArrayList<>();

    public User(String login,String password,String name,String surname,ServiceRole serviceRole) {
        this.login=login;
        this.password=password;
        this.name=name;
        this.surname=surname;
        this.serviceRole=serviceRole;
    }

    public User(String login,String password,City city,String title,ServiceRole serviceRole) {
        this.login=login;
        this.password=password;
        this.city = city;
        this.name = title;
        this.serviceRole=serviceRole;
    }

    public User(String login,String password,ServiceRole serviceRole) {
        this.login=login;
        this.password=password;
        this.serviceRole=serviceRole;
    }

    public User(String login,String password,String name,ServiceRole serviceRole) {
        this.login=login;
        this.password=password;
        this.name=name;
        this.serviceRole=serviceRole;
    }

    public User(String login) {
        this.login=login;
    }

    public Player getPlayer() {
        if(!player.isEmpty()) {
            return player.get(0);
        }
        return null;
    }

    public Scout getScout() {
        if(!scout.isEmpty()) {
            return scout.get(0);
        }
        return null;
    }

    public Academy getAcademy() {
        if(!academy.isEmpty()) {
            return academy.get(0);
        }
        return null;
    }

    public Club getClub() {
        if(!club.isEmpty()) {
            return club.get(0);
        }
        return null;
    }

    public String getNameAndSurname() {
        return name + " " + surname;
    }

}
