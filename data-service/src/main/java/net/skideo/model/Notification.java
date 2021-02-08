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
    @Enumerated(EnumType.STRING)
    private NotificationEnum notificationEnum;

    @ManyToOne
    private Academy academy;

    public Notification(NotificationEnum notificationEnum) {
        this.notificationEnum=notificationEnum;
    }

}
