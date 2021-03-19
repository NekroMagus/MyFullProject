package net.skideo.service.notification;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.NotificationInfoDto;
import net.skideo.model.Info;
import net.skideo.model.Notification;
import net.skideo.model.User;
import net.skideo.model.enums.NotificationEnum;
import net.skideo.repository.NotificationRepository;
import net.skideo.service.info.InfoService;
import net.skideo.service.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final InfoService infoService;
    private final UserService userService;
    private final NotificationRepository repository;
    private final JavaMailSender mailSender;

    @Value("${spring.email.username}")
    private String username;

    @Override
    public void addNotification(NotificationEnum notificationEnum, String message, long idUser) {
        Info currentInfo = infoService.getCurrentInfo();
        User user = userService.getUserById(idUser);

        Notification notification = new Notification(notificationEnum, message,currentInfo,user);

        user.getNotification().add(notification);

        userService.save(user);

        sendNotification(user.getInfo().getEmail(),notificationEnum.getNotification() + " from " + currentInfo.getName() + ": \n" + message);
    }

    @Override
    public Page<NotificationInfoDto> getMyNotifications(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        User currentUser = userService.getCurrentUser();

        return repository.findByUserId(currentUser.getId(),pageable);
    }

    private void sendNotification(String email,String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("odincovegor72@gmail.com");
        mailMessage.setTo("odincovegor72@gmail.com");
        mailMessage.setSubject("Skideo notification");
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

}
