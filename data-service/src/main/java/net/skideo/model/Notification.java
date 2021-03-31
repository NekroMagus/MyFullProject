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
    private Info senderInfo;

    @ManyToOne
    private Player player;

    public Notification(NotificationEnum notificationEnum, String message, Info senderInfo, Player player) {
        this.notificationEnum = notificationEnum;
        this.message = message;
        this.senderInfo = senderInfo;
        this.player = player;
    }

}
