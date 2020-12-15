package net.skideo.repository;

import net.skideo.JpaTest;
import net.skideo.dto.projections.TestProjection;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Like;
import net.skideo.model.User;
import net.skideo.model.enums.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JpaTest
public class LikeRepositoryTest {

    @Autowired
    private LikeRepository repository;
    private final long ID = 1L;

    @Test
    public void contextLoads() {
        assertNotNull(repository);
    }

    @Test
    public void givenValidId_whenFindById_thenNotNull() {
        Like like = repository.findById(ID).orElseThrow(
                () -> new NotFoundException("Like not found")
        );

        assertNotNull(like);
    }

    @Test
    public void givenInvalidId_whenFindById_thenNotFound() {
        final long id = 10000L;

        assertThrows(NotFoundException.class, () -> repository.findById(id).orElseThrow(
                () -> new NotFoundException("Like not found")
        ));
    }

    @Test
    public void givenLike_whenSaveAndFindById_thenEquals() {
        Like like = new Like();
        like.setId(ID);
        like.setRating(Rating.TWO);

        repository.save(like);

        assertEquals(repository.findById(ID).orElseThrow(
                () -> new NotFoundException("Like not found")
        ).getRating(), Rating.TWO);
    }

    @Test
    public void givenValidId_whenDeleteByIdAndFindById_thenNotFound() {
        repository.deleteById(ID);

        assertThrows(NotFoundException.class, () -> repository.findById(ID).orElseThrow(
                () -> new NotFoundException("Like not found")
        ));
    }

    @Test
    public void givenValidId_whenFindByIdAndFindByUserIdAndVideoId_thenEquals() {
        Like like1 = repository.findById(ID).orElseThrow(
                () -> new NotFoundException("Like not found")
        );
        Like like2 = repository.findByUserIdAndVideoId(1, 1).orElseThrow(
                () -> new NotFoundException("Like not found")
        );

        assertEquals(like1, like2);
    }

}