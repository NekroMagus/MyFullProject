package net.skideo.repository;

import net.skideo.dto.NotificationInfoDto;
import net.skideo.model.Notification;
import net.skideo.model.enums.NotificationEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    Page<Notification> findByToId(long toId, Pageable pageable);

    List<Notification> findByNotificationTypeAndMessageAndToId(NotificationEnum notificationType,String message, long toId);

}
