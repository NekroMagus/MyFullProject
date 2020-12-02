package net.skideo.repository;

import net.skideo.JpaTest;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Like;
import net.skideo.model.User;
import net.skideo.model.enums.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JpaTest
public class LikeRepositoryTest {

    @Autowired
    private LikeRepository repository;

    @Test
    public void contextLoads() {
        assertNotNull(repository);
    }

    @Test
    public void findByIdTest_NotNull() {
        Like like = repository.findById(1L).orElseThrow(
                () -> new NotFoundException("Like not found")
        );
        assertNotNull(like);
    }

    @Test
    public void findByIdTest_Throws() {
        assertThrows(NotFoundException.class, () -> repository.findById(4L).orElseThrow(
                () -> new NotFoundException("Like not found")
        ));
    }

    @Test
    public void updateTest() {
        Like like = new Like();
        like.setId(1L);
        like.setRating(Rating.TWO);

        repository.save(like);

        assertEquals(repository.findById(1L).orElseThrow(
                () -> new NotFoundException("Like not found")
        ).getRating(), Rating.TWO);
    }

    @Test
    public void deleteByIdTest() {
        repository.deleteById(1L);

        assertThrows(NotFoundException.class, () -> repository.findById(1L).orElseThrow(
                () -> new NotFoundException("Like not found")
        ));
    }

    @Test
    public void findByUserIdAndVideoId() {
        Like like1 = repository.findById(1L).orElseThrow(
                () -> new NotFoundException("Like not found")
        );
        Like like2 = repository.findByUserIdAndVideoId(1, 1).orElseThrow(
                () -> new NotFoundException("Like not found")
        );

        assertEquals(like1, like2);
    }

}