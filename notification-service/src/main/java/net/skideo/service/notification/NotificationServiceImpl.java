package net.skideo.service.notification;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.NotificationInfoDto;
import net.skideo.model.User;
import net.skideo.model.Notification;
import net.skideo.model.Player;
import net.skideo.model.enums.NotificationEnum;
import net.skideo.repository.NotificationRepository;
import net.skideo.service.player.PlayerService;
import net.skideo.service.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final UserService userService;
    private final PlayerService playerService;
    private final NotificationRepository repository;
    private final JavaMailSender mailSender;

    Logger log = Logger.getLogger(NotificationServiceImpl.class.getName());

    @Value("${spring.email.username}")
    private String username;

    @Override
    public void addNotification(NotificationEnum notificationEnum, String message, long idUser) {
        User currentUser = userService.getCurrentUser(); // from
        Player player = playerService.getUserById(idUser); // to

        Notification similarNotification = getSimilarNotification(notificationEnum,message,player.getUser().getId());

        if(similarNotification==null) {
            Notification notification = new Notification(notificationEnum, message, currentUser, player.getUser(),true,null);
            player.getUser().getNotifications().add(notification);

            playerService.save(player);

            sendNotification(player.getUser().getEmail(), notificationEnum.getNotification() + " from " + currentUser.getName() + ": \n" + message);
        }
        else {
            Notification notification = new Notification(notificationEnum,message,currentUser,player.getUser(),false,similarNotification);
            player.getUser().getNotifications().add(notification);

            similarNotification.getSimilarNotifications().add(notification);

            repository.save(similarNotification);

            Set<Notification> similarNotifications = similarNotification.getSimilarNotifications();
            sendNotification(player.getUser().getEmail(), notificationEnum.getNotification() + " from " + currentUser.getName() + " and " + (similarNotifications.size()>1 ? similarNotifications.size() + " more people" : get(0,similarNotifications).getFrom().getNameAndSurname()) + ": \n" + similarNotification.getMessage());
        }
    }

    @Override
    public Page<Notification> getMyNotifications(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Player currentPlayer = playerService.getCurrentPlayer();

        return repository.findByToId(currentPlayer.getUser().getId(),pageable);
    }

    private void sendNotification(String email,String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(email);
        mailMessage.setSubject("Skideo notification");
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

    private Notification getSimilarNotification(NotificationEnum notificationType,String message, long idUser) {
        List<Notification> notifications = repository.findByNotificationTypeAndMessageAndToId(notificationType,message,idUser);
        for(Notification notification : notifications) {
            if (Duration.between(LocalDateTime.now(), notification.getCreated()).toHours() <= 5) {
                return notification;
            }
        }
        return null;
    }

    private Notification get(int index, Set<Notification> notifications) {
        int i = 0;
        for(Notification notification : notifications) {
            if(i==index) {
                return notification;
            }
            i++;
        }
        return null;
    }

}
