package net.skideo.model;

import lombok.*;
import net.skideo.model.abstracts.BaseEntity;
import net.skideo.model.enums.NotificationEnum;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"similarNotifications"})
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification extends BaseEntity {

    private String message;
    private Boolean isHighLevel;
    @Enumerated(EnumType.STRING)
    private NotificationEnum notificationType;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private User toUser;

    @OneToMany(cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
    private List<Notification> similarNotifications=new LinkedList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Notification highLevelNotification;

    public Notification(NotificationEnum notificationType, String message, User from, User to,boolean isHighLevel,Notification highLevelNotification) {
        this.notificationType = notificationType;
        this.message = message;
        this.fromUser = from;
        this.toUser = to;
        this.isHighLevel=isHighLevel;
        this.highLevelNotification=highLevelNotification;
    }

}
