package net.skideo.service.notification;

import net.skideo.model.Notification;

public interface NotificationService {

    void addNotification(Notification notification, long idUser);

}
