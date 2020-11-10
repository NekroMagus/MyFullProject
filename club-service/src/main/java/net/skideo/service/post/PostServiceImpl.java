package net.skideo.service.post;

import lombok.RequiredArgsConstructor;
import net.skideo.repository.PostRepository;
import net.skideo.exception.PostNotFoundException;
import net.skideo.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post findById(long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException("Post not found")
        );
    }
}
