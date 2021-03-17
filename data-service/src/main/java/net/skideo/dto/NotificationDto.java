package net.skideo.dto;

import lombok.Data;
import net.skideo.model.enums.NotificationEnum;

@Data
public class NotificationDto {

    private NotificationEnum notificationEnum;
    private long idUser;

}
