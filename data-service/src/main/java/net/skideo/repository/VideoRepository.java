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

    @EntityGraph(attributePaths = {"likes"})
    Page<VideoDto> findAllByInfoId(long infoId, Pageable pageable);

    @EntityGraph(attributePaths = {"likes"})
    Page<VideoDto> findByInfoIdNot(long infoId, Pageable pageable);

    List<Video> findAllByInfoId(long infoId);


}
