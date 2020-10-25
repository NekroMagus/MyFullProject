package data.service.dao;

import data.service.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoDao  extends JpaRepository<Video,Long> {

}
