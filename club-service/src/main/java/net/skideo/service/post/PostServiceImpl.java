package net.skideo.service.post;

import net.skideo.dao.PostDao;
import net.skideo.exception.PostNotFoundException;
import net.skideo.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public void save(Post post) {
        postDao.save(post);
    }

    @Override
    public Post findById(long id) {
        return postDao.findById(id).orElseThrow(
                () -> new PostNotFoundException("Post not found")
        );
    }
}
