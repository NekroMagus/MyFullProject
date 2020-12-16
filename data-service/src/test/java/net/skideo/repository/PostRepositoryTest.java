package net.skideo.repository;

import net.skideo.exception.PostNotFoundException;
import net.skideo.model.Post;
import net.skideo.JpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@JpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository repository;
    private final long ID = 1L;

    @Test
    public void givenValidId_whenFindById_thenNotNull() {
        Post post = repository.findById(ID).orElseThrow(
                () -> new PostNotFoundException("Post not found")
        );

        assertNotNull(post);
    }

    @Test
    public void givenInvalidId_whenFindById_thenNotFound() {
        final long ID = 1000000L;

        assertThrows(PostNotFoundException.class,() ->
            repository.findById(ID).orElseThrow(
                    () -> new PostNotFoundException("Post bot found")
            )
        );
    }

    @Test
    public void givenValidId_whenDeleteByIdAndFindById_thenEquals() {
        repository.deleteById(ID);

        assertEquals(Optional.empty(),repository.findById(ID));
    }

    @Test
    public void givenValidIdAndPost_whenSaveAndFindById_thenEquals() {
        Post newPost = new Post();
        newPost.setId(ID);
        newPost.setAgent(true);
        newPost.setCountry("USA");

        repository.save(newPost);

        Post post = repository.findById(ID).orElseThrow(
                () -> new PostNotFoundException("Post not found")
        );

        assertEquals(post.getCountry(),newPost.getCountry());
        assertEquals(post.isAgent(),newPost.isAgent());
    }

}
