package net.skideo.model;

import lombok.*;
import net.skideo.model.enums.NotificationEnum;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "skideo_notification")
public class Notification extends BaseEntity {

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
