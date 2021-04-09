package net.skideo.model;

import lombok.*;
import net.skideo.model.abstracts.BaseEntity;
import net.skideo.model.enums.NotificationEnum;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification extends BaseEntity {

    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationEnum notificationEnum;

    @ManyToOne
    private User senderUser;

    @ManyToOne
    private User user;

    public Notification(NotificationEnum notificationEnum, String message, User senderUser, User user) {
        this.notificationEnum = notificationEnum;
        this.message = message;
        this.senderUser = senderUser;
        this.user = user;
    }

}
