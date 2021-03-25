package net.skideo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import net.skideo.model.enums.Role;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RolePeople;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "skideo_user")
public class User extends AbstractEntity {

    @CreatedDate
    @JsonFormat(pattern = "dd-MM-yyyy--HH-mm-SS")
    private LocalDateTime created;

    @LastModifiedDate
    @JsonFormat(pattern = "dd-MM-yyyy--HH-mm-SS")
    private LocalDateTime updated;

    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private Info info;

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
        this.info=info;
        this.info.setRolePeople(rolePeople);
        this.hasAgent = hasAgent;

        this.role = Role.UNCONFIRMED;
    }

}
