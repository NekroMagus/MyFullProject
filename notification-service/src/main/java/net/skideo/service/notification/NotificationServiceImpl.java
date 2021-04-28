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

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
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
    @Transactional
    public void addNotification(NotificationEnum notificationEnum, String message, long idUser) {
        User currentUser = userService.getCurrentUser(); // from
        Player player = playerService.getPlayerById(idUser); // to

        Notification similarNotification = repository.findFirstByNotificationTypeAndMessageAndToUserIdAndCreatedBetween(notificationEnum,message,player.getUser().getId(),LocalDateTime.now().minusHours(5),LocalDateTime.now());

        if(similarNotification==null) {
            Notification notification = new Notification(notificationEnum, message, currentUser, player.getUser(),true,null);
            player.getUser().getNotifications().add(notification);

            userService.save(player.getUser());

            sendNotification(player.getUser().getEmail(), notificationEnum.getNotification() + " from " + currentUser.getName() + ": \n" + message);
        }
        else {
            Notification notification = new Notification(notificationEnum,message,currentUser,player.getUser(),false,similarNotification);
            player.getUser().getNotifications().add(notification);

            similarNotification.getSimilarNotifications().add(notification);

            userService.save(player.getUser());
            repository.save(similarNotification);

            List<Notification> similarNotifications = similarNotification.getSimilarNotifications();
            sendNotification(player.getUser().getEmail(), notificationEnum.getNotification() + " from " + currentUser.getName() + " and " + (similarNotifications.size()>1 ? similarNotifications.size() + " more people" : similarNotifications.get(0).getFromUser().getNameAndSurname()) + ": \n" + similarNotification.getMessage());
        }
    }

    @Override
    public Page<NotificationInfoDto> getMyNotifications(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Player currentPlayer = playerService.getCurrentPlayer();

        return repository.findByToUserId(currentPlayer.getUser().getId(),pageable);
    }

    private void sendNotification(String email,String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(email);
        mailMessage.setSubject("Skideo notification");
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

}
