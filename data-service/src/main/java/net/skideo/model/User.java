package net.skideo.model;

import lombok.*;
import net.skideo.model.enums.Role;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RolePeople;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "skideo_user")
public class User extends AbstractInfoEntity {

    @Enumerated(EnumType.STRING)
    private Role role;
    private String phone;

    @Enumerated(value = EnumType.STRING)
    private LeadingLeg leadingLeg;

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    private boolean hasAgent;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String linkSocialNetwork;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.LAZY)
    private List<Notification> notification = new LinkedList<>();

    public User(Info info, RolePeople rolePeople, boolean hasAgent) {
        setInfo(info);
        getInfo().setRolePeople(rolePeople);
        this.hasAgent = hasAgent;

        this.role = Role.UNCONFIRMED;
    }

}
