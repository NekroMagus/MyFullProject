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
        User currentUser = userService.getCurrentUser();
        Player player = playerService.getUserById(idUser);

        Notification notification = new Notification(notificationEnum, message, currentUser, player.getUser());

        player.getUser().getNotification().add(notification);

        playerService.save(player);

        sendNotification(player.getUser().getEmail(),notificationEnum.getNotification() + " from " + currentUser.getName() + ": \n" + message);
    }

    @Override
    public Page<NotificationInfoDto> getMyNotifications(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Player currentPlayer = playerService.getCurrentUser();

        return repository.findByUserId(currentPlayer.getId(),pageable);
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
