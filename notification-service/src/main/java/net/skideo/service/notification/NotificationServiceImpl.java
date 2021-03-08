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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.LinkedList;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final AcademyService academyService;
    private final UserService userService;
    private final NotificationRepository repository;
    private final JavaMailSender mailSender;

    @Value("${spring.email.username}")
    private String username;

    @Override
    public void addNotification(NotificationEnum notificationEnum, long idUser) throws MessagingException {
        Academy currentAcademy = academyService.getCurrentAcademy();
        User user = userService.getUserById(idUser);

        Notification notification = new Notification(notificationEnum);
        notification.setAcademy(currentAcademy);
        notification.setUser(user);

        user.getNotification().add(notification);

        //userService.save(user);

        sendNotification(user.getInfo().getEmail(),notificationEnum.getNotification() + " by " + currentAcademy.getInfo().getName());
    }

    @Override
    public Page<NotificationInfoDto> getMyNotifications(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        User currentUser = userService.getCurrentUser();

        return repository.findByUserId(currentUser.getId(),pageable);
    }

    private void sendNotification(String email,String message) throws MessagingException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,true);

        mimeMessageHelper.setFrom(username);
        mimeMessageHelper.setTo("fli_igor@mail.ru");
        mimeMessageHelper.setSubject("Java");
        mimeMessageHelper.setText("<img src='cid:gora' width='200px' height='200px'>",true);

        File file = new File("C:/Games-2/gora.jpg");
        mimeMessageHelper.addInline("gora",file);

        mailSender.send(mimeMailMessage);
    }

}
