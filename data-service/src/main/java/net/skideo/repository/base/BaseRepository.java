package net.skideo.repository.base;

import net.skideo.dto.projections.IdProjection;
import net.skideo.model.abstracts.AbstractUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@NoRepositoryBean
public interface BaseRepository<T extends AbstractUserEntity> extends JpaRepository<T,Long> {

    Optional<T> findByUserLogin(String login);

    Optional<IdProjection> findIdByUserLogin(String login);

}
