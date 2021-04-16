package net.skideo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.AbstractEntity;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import net.skideo.model.enums.ServiceRole;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.LAZY)
    private Set<Notification> notifications = new LinkedHashSet<>();

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Player player;

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

}
