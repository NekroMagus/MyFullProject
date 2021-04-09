package net.skideo.repository;

import net.skideo.dto.PostDto;
import net.skideo.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PostRepository extends JpaRepository<Post,Long> {

    Page<PostDto> findAllByClubId(long clubId, Pageable pageable);

}
