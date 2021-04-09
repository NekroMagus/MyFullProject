package net.skideo.service.post;

import net.skideo.dto.PostDto;
import net.skideo.model.Post;
import org.springframework.data.domain.Page;

public interface PostService {

    void save(Post post);

    Post findById(long id);

    Page<PostDto> getMyPosts(int page,int size);

}
