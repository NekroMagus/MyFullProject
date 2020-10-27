package net.skideo.dao;

import net.skideo.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeDao extends JpaRepository<Like,Long> {
}
