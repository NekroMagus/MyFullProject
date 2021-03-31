package net.skideo.repository;

import net.skideo.dto.NotificationInfoDto;
import net.skideo.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    Page<NotificationInfoDto> findByPlayerId(long playerId, Pageable pageable);

}
