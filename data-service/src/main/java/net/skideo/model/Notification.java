package net.skideo.model;

import lombok.*;
import net.skideo.model.abstracts.BaseEntity;
import net.skideo.model.enums.NotificationEnum;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification extends BaseEntity {

    private String message;
    private boolean isHighLevel;
    @Enumerated(EnumType.STRING)
    private NotificationEnum notificationType;

    @ManyToOne
    private User from;

    @ManyToOne
    private User to;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<Notification> similarNotifications;

    @ManyToOne(optional = false)
    private Notification highLevelNotification;

    public Notification(NotificationEnum notificationType, String message, User from, User to,boolean isHighLevel,Notification highLevelNotification) {
        this.notificationType = notificationType;
        this.message = message;
        this.from = from;
        this.to = to;
        this.isHighLevel=isHighLevel;
        this.highLevelNotification=highLevelNotification;
    }

}
