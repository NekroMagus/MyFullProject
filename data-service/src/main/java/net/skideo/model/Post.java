package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.BaseEntity;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "skideo_post")
public class Post extends BaseEntity {

    private String country;
    private RoleFootball roleFootball;
    private boolean agent;
    private RolePeople rolePeople;
    private LeadingLeg leadingLeg;
    private LocalDate birthDate;
    private String videoLink;
    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    public Post(Club club, String country, RoleFootball roleFootball, boolean agent, RolePeople rolePeople,
                LeadingLeg leadingLeg, LocalDate birthDate, String videoLink) {
        this.club = club;
        this.country = country;
        this.roleFootball = roleFootball;
        this.agent = agent;
        this.rolePeople = rolePeople;
        this.leadingLeg = leadingLeg;
        this.birthDate = birthDate;
        this.videoLink = videoLink;
    }

}
