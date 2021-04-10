package net.skideo.repository;

import net.skideo.dto.VideoDto;
import net.skideo.model.enums.ServiceRole;
import net.skideo.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select new net.skideo.dto.VideoDto(v) from Video v inner join v.likes l group by v.id order by cast(sum(l.rating) as float)/v.likes.size", countQuery = "select count(v) from Video v")
    Page<VideoDto> findPopularVideoByUserServiceRole(ServiceRole serviceRole,Pageable pageable);

}

