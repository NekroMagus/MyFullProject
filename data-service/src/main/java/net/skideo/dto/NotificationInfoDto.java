package net.skideo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Notification;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class NotificationInfoDto {

    private String notificationValue;
    private String academyTitle;
    private String text;
    private String dateCreated;

    public NotificationInfoDto(Notification notification) {
        this.notificationValue=notification.getNotificationEnum().getNotification();
        this.academyTitle=notification.getSenderUser().getName();
        this.text=notification.getMessage();
        this.dateCreated = OffsetDateTime.of(notification.getCreated(), ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss.SSSxxx"));
    }

}
