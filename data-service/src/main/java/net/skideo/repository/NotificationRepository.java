package net.skideo.repository;

import net.skideo.dto.NotificationInfoDto;
import net.skideo.model.Notification;
import net.skideo.model.enums.NotificationEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    Page<NotificationInfoDto> findByToUserId(long toUserId, Pageable pageable);

    Notification findTopByNotificationTypeAndMessageAndToUserIdAndCreatedBetween(NotificationEnum notificationType, String message, long toUserId,LocalDateTime created,LocalDateTime now);

}
