package net.skideo.repository;

import net.skideo.dto.VideoDto;
import net.skideo.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video,Long> {

    @EntityGraph(attributePaths = {"user"})
    Optional<Video> findById(long id);

    @EntityGraph(attributePaths = {"user", "likes"})
    Page<VideoDto> findAllByUserId(Long userId, Pageable pageable);

    @EntityGraph(attributePaths = {"user", "likes"})
    Page<VideoDto> findByUserIdNot(Long userId, Pageable pageable);

    List<Video> findAllByUserId(Long userId);


}
