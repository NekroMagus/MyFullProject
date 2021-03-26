package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "club")
public class Club extends BaseEntity {

    private String logoLink;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Info info;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> favoriteUsers = new LinkedHashSet<>();

    public Club(Info info,String logoLink) {
        this.info = info;
        this.logoLink = logoLink;
    }

    public Club(String login,String password,String titleClub) {
        this.info.setLogin(login);
        this.info.setPassword(password);
        this.info.setName(titleClub);
    }

}
