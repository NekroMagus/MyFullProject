package net.skideo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.enums.Role;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import net.skideo.model.enums.ServiceRole;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="skideo_info")
public class Info extends AbstractEntity {

    @Column(unique = true)
    private String login;
    @Column(unique = true)
    private String password;
    @Column(unique = true)
    private String email;
    private String name;
    private String surname;
    private String city;
    private String country;
    @Enumerated(value = EnumType.STRING)
    private RoleFootball roleFootball;
    @Enumerated(value = EnumType.STRING)
    private ServiceRole serviceRole;
    @Enumerated(value = EnumType.STRING)
    private RolePeople rolePeople;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Info(String login,String password,String city,String country,String title) {
        this.login = login;
        this.password = password;
        this.city = city;
        this.country = country;
        this.name = title;
    }

    public Info(String login,String password,String name) {
        this.login=login;
        this.password=password;
        this.name=name;
    }

    public Info(String login,String password) {
        this.login=login;
        this.password=password;
    }

    public Info(String login,String password,String name,String surname) {
        this.login=login;
        this.password=password;
        this.name=name;
        this.surname=surname;
    }

    public Info(String login) {
        this.login=login;
    }

}
