package net.skideo.model;

import lombok.EqualsAndHashCode;
import net.skideo.dto.ClubRegDto;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode
@Entity
@Table(name="club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    private String titleClub;
    private String logoLink;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> favoriteUsers;

    public Club() {}

    public Club(String login,String password,String titleClub,String logoLink) {
        this.login=login;
        this.password=password;
        this.titleClub=titleClub;
        this.logoLink=logoLink;
    }

    public Club(ClubRegDto regDto) {
        this.login=regDto.getLogin();
        this.password=regDto.getPassword();
        this.titleClub=regDto.getTitle();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitleClub() {
        return titleClub;
    }

    public void setTitleClub(String titleClub) {
        this.titleClub = titleClub;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }

    public Set<User> getFavoriteUsers() {
        return favoriteUsers;
    }

    public void setFavoriteUsers(Set<User> favoriteUsers) {
        this.favoriteUsers = favoriteUsers;
    }
}
