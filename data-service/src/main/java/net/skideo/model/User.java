package net.skideo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.AbstractEntity;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import net.skideo.model.enums.ServiceRole;

import javax.persistence.*;

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
    @ManyToOne(cascade = CascadeType.MERGE)
    private City city;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Country country;
    @Enumerated(value = EnumType.STRING)
    private ServiceRole serviceRole;

    /* Fields for players */
    @Enumerated(value = EnumType.STRING)
    private RoleFootball roleFootball;
    @Enumerated(value = EnumType.STRING)
    private RolePeople rolePeople;
    /* ------------------ */

    public User(String login,String password,String name,String surname,ServiceRole serviceRole) {
        this.login=login;
        this.password=password;
        this.name=name;
        this.surname=surname;
        this.serviceRole=serviceRole;
    }

    public User(String login,String password,City city,Country country,String title,ServiceRole serviceRole) {
        this.login=login;
        this.password=password;
        this.city = city;
        this.country = country;
        this.name = title;
        this.serviceRole=serviceRole;
    }

    public User(String login,String password,RolePeople rolePeople,ServiceRole serviceRole) {
        this.login=login;
        this.password=password;
        this.rolePeople=rolePeople;
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
