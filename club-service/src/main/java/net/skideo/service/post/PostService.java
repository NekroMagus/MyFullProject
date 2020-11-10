package net.skideo.service.post;

import net.skideo.model.Post;

public interface PostService {

    void save(Post post);

    Post findById(long id);
}
