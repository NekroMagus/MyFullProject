package net.skideo.service.notification;

import lombok.RequiredArgsConstructor;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Academy;
import net.skideo.model.Notification;
import net.skideo.model.User;
import net.skideo.model.enums.NotificationEnum;
import net.skideo.repository.AcademyRepository;
import net.skideo.service.academy.AcademyService;
import net.skideo.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.LinkedList;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final AcademyService academyService;
    private final UserService userService;
    private final AcademyRepository repository;

    @Override
    public void addNotification(NotificationEnum notificationEnum, long idUser) {
        Academy currentAcademy = repository.findById(19L).orElseThrow(()-> new NotFoundException("Academy not found"));
        User user = userService.getUserById(idUser);
        Notification notification = new Notification(notificationEnum);
        notification.setAcademy(currentAcademy);

        if(user.getNotification()==null) {
            user.setNotification(new LinkedList<>());
        }
        user.getNotification().add(notification);

        userService.updateNotifications(user);

    }
}
