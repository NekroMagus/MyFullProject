package net.skideo.repository;

import net.skideo.dto.VideoDto;
import net.skideo.model.enums.ServiceRole;
import net.skideo.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VideoRepository extends JpaRepository<Video, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,attributePaths = {"likes","comments"})
    Optional<Video> findById(long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,attributePaths = {"likes","comments"})
    Page<VideoDto> findAllByUserId(long userId, Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,attributePaths = {"likes","comments"})
    Page<VideoDto> findByUserIdNotAndUserServiceRole(long userId, ServiceRole serviceRole, Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"likes","comments"})
    List<VideoDto> findAllByUserIdNotAndUserServiceRole(long userId,ServiceRole serviceRole);

    List<Video> findAllByUserId(long userId);

    Page<VideoDto> findAllByUserServiceRole(ServiceRole serviceRole,Pageable pageable);

    List<Video> findAllByUserServiceRole(ServiceRole serviceRole);

}
