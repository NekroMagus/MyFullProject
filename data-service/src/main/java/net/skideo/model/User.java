package net.skideo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.skideo.model.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RolePeople;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "skideo_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @Enumerated(value = EnumType.STRING)
    private RolePeople rolePeople;

    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;

    private boolean hasAgent;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String linkSocialNetwork;

    public User(Info info, RolePeople rolePeople, boolean hasAgent) {
        this.info=info;
        this.rolePeople = rolePeople;
        this.hasAgent = hasAgent;

        this.role = Role.UNCONFIRMED;
    }
}
