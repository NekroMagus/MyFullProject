package net.skideo.repository.base;

import net.skideo.dto.projections.IdProjection;
import net.skideo.model.abstracts.AbstractInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@NoRepositoryBean
public interface BaseRepository<T extends AbstractInfoEntity> extends JpaRepository<T,Long> {

    Optional<T> findByInfoLogin(String login);

    Optional<IdProjection> findIdByInfoLogin(String login);

}
