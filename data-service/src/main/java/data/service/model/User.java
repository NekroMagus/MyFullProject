package data.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import data.service.model.enums.LeadingLeg;
import data.service.model.enums.RolePeople;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import data.service.model.enums.Role;
import data.service.model.enums.RoleFootball;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
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


    private String login;
    private String email;
    private String password;
    private boolean active;
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private RoleFootball roleFootball;
    private String name;
    private String surname;
    private String telephoneNumber;
    private String country;
    private String city;

    @Enumerated(value = EnumType.STRING)
    private LeadingLeg leadingLeg;

    @Enumerated(value = EnumType.STRING)
    private RolePeople rolePeople;

    private String club;
    private boolean agent;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private Timestamp dateOfRegistration;
    private String socialNetwork;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Scout> listScoutsUploadedVideo;

    @OneToMany(cascade = CascadeType.PERSIST,targetEntity = Video.class,fetch = FetchType.LAZY)
    private List<Video> videos;

    @OneToMany(targetEntity = Like.class,fetch = FetchType.LAZY)
    private List<Like> likes;

}