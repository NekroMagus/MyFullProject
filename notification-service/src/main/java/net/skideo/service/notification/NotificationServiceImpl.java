package net.skideo.service.notification;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.NotificationInfoDto;
import net.skideo.model.Academy;
import net.skideo.model.Notification;
import net.skideo.model.User;
import net.skideo.model.enums.NotificationEnum;
import net.skideo.repository.NotificationRepository;
import net.skideo.service.academy.AcademyService;
import net.skideo.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final AcademyService academyService;
    private final UserService userService;
    private final NotificationRepository repository;

    @Override
    public void addNotification(NotificationEnum notificationEnum, long idUser) {
        Academy currentAcademy = academyService.getCurrentAcademy();
        User user = userService.getUserById(idUser);

        Notification notification = new Notification(notificationEnum);
        notification.setAcademy(currentAcademy);
        notification.setUser(user);

        user.getNotification().add(notification);

        userService.save(user);
    }

    @Override
    public Page<NotificationInfoDto> getMyNotifications(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        User currentUser = userService.getCurrentUser();

        return repository.findByUserId(currentUser.getId(),pageable);
    }

}
