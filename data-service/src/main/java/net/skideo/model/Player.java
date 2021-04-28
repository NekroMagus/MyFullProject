package net.skideo.model;

import lombok.*;
import net.skideo.model.abstracts.AbstractUserEntity;
import net.skideo.model.enums.Role;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_player")
public class Player extends AbstractUserEntity {

    @Enumerated(EnumType.STRING)
    private Role role;
    private String phone;
    @Enumerated(value = EnumType.STRING)
    private LeadingLeg leadingLeg;
    @Enumerated(value = EnumType.STRING)
    private RoleFootball roleFootball;
    @Enumerated(value = EnumType.STRING)
    private RolePeople rolePeople;

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    private Boolean hasAgent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String linkSocialNetwork;

    public Player(User user,RolePeople rolePeople, boolean hasAgent) {
        setUser(user);
        this.rolePeople=rolePeople;
        this.hasAgent = hasAgent;

        this.role = Role.UNCONFIRMED;
    }

}
