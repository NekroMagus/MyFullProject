package net.skideo.repository;

import net.skideo.dto.projections.TestProjection;
import net.skideo.model.Like;
import net.skideo.model.User;
import org.aspectj.weaver.ast.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {

    Optional<Like> findByUserIdAndVideoId(long userId, long videoId);

}
