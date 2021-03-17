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

public interface VideoRepository extends JpaRepository<Video, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,attributePaths = {"likes","comments"})
    Optional<Video> findById(long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,attributePaths = {"likes","comments"})
    Page<VideoDto> findAllByInfoId(long infoId, Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,attributePaths = {"likes","comments"})
    Page<VideoDto> findByInfoIdNotAndInfoServiceRole(long infoId, ServiceRole serviceRole, Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"likes","comments"})
    List<VideoDto> findAllByInfoIdNotAndInfoServiceRole(long infoId,ServiceRole serviceRole);

    List<Video> findAllByInfoId(long infoId);

    Page<VideoDto> findAllByInfoServiceRole(ServiceRole serviceRole,Pageable pageable);

    List<Video> findAllByInfoServiceRole(ServiceRole serviceRole);

}
