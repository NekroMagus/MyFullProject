package net.skideo.dao;

import net.skideo.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoDao  extends JpaRepository<Video,Long> {

}
