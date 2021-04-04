package net.skideo.repository.base;

import net.skideo.model.abstracts.AbstractLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@NoRepositoryBean
public interface LocationRepository<T extends AbstractLocationEntity> extends JpaRepository<T,Long> {

    Optional<T> findByName(String name);

}
