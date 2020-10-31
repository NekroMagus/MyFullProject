package net.skideo.dao;

import net.skideo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDao extends JpaRepository<Post,Long> {
}
