package net.skideo.repository;

import net.skideo.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {

    Optional<Like> findByInfoIdAndVideoId(long infoId,long videoId);

}
