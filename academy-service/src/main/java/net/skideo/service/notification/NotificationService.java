package net.skideo.service.notification;


import net.skideo.model.enums.NotificationEnum;

public interface NotificationService {

    void addNotification(NotificationEnum notificationEnum, long idUser);

}
