package net.skideo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scout")
public class Scout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String region;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Info info;

    @ManyToOne
    private Club club;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> favoriteUsers = new LinkedHashSet<>();

    public Scout(String login,String password,String name,String surname) {
        this.info.setLogin(login);
        this.info.setPassword(password);
        this.info.setName(name);
        this.info.setSurname(surname);
    }

}
