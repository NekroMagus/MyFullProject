package net.skideo.service.notification;

import net.skideo.dto.NotificationInfoDto;
import net.skideo.model.Notification;
import net.skideo.model.enums.NotificationEnum;
import org.springframework.data.domain.Page;

import javax.mail.MessagingException;

public interface NotificationService {

    void addNotification(NotificationEnum notificationEnum, String message, long idUser) throws MessagingException;

    Page<NotificationInfoDto> getMyNotifications(int page, int size);

}
