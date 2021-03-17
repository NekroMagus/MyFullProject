package net.skideo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Notification;
import net.skideo.model.enums.NotificationEnum;

@Data
@NoArgsConstructor
public class NotificationInfoDto {

    private String notificationValue;
    private String academyTitle;

    public NotificationInfoDto(Notification notification) {
        this.notificationValue=notification.getNotificationEnum().getNotification();
        this.academyTitle=notification.getAcademy().getInfo().getName();
    }

}
