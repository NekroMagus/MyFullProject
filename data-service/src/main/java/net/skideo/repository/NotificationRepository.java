package net.skideo.repository;

import net.skideo.dto.NotificationInfoDto;
import net.skideo.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    Page<NotificationInfoDto> findByUserId(long userId, Pageable pageable);

}
