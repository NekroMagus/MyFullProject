package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.enums.NotificationEnum;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "skideo_notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationEnum notificationEnum;

    @ManyToOne
    private Info senderInfo;

    @ManyToOne
    private User user;

    public Notification(NotificationEnum notificationEnum,String message, Info senderInfo,User user) {
        this.notificationEnum = notificationEnum;
        this.message = message;
        this.senderInfo = senderInfo;
        this.user = user;
    }

}
