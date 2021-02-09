package net.skideo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Notification;
import net.skideo.model.enums.NotificationEnum;

@Data
@NoArgsConstructor
public class NotificationInfoDto {

    private NotificationEnum notificationEnum;
    private String academyTitle;

    public NotificationInfoDto(Notification notification) {
        this.notificationEnum=notification.getNotificationEnum();
        this.academyTitle=notification.getAcademy().getInfo().getName();
    }

}
