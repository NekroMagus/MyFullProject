package net.skideo.repository;

import net.skideo.dto.VideoDto;
import net.skideo.model.enums.ServiceRole;
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

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,attributePaths = {"likes"})
    Page<VideoDto> findAllByInfoId(long infoId, Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,attributePaths = {"likes"})
    Page<VideoDto> findByInfoIdNotAndInfoServiceRole(long infoId, ServiceRole serviceRole, Pageable pageable);

    List<Video> findAllByInfoId(long infoId);


}
