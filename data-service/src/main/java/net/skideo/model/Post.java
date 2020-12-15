package net.skideo.model;

import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="skideo_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String country;
    private RoleFootball roleFootball;
    private boolean agent;
    private RolePeople rolePeople;
    private LeadingLeg leadingLeg;
    private LocalDate dateOfBirth;
    private String videoLink;
    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public RoleFootball getRoleFootball() {
        return roleFootball;
    }

    public void setRoleFootball(RoleFootball roleFootball) {
        this.roleFootball = roleFootball;
    }

    public boolean isAgent() {
        return agent;
    }

    public void setAgent(boolean agent) {
        this.agent = agent;
    }

    public RolePeople getRolePeople() {
        return rolePeople;
    }

    public void setRolePeople(RolePeople rolePeople) {
        this.rolePeople = rolePeople;
    }

    public LeadingLeg getLeadingLeg() {
        return leadingLeg;
    }

    public void setLeadingLeg(LeadingLeg leadingLeg) {
        this.leadingLeg = leadingLeg;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
