package net.skideo.service.like;

import net.skideo.exception.NotFoundException;
import net.skideo.model.Like;
import net.skideo.model.enums.Rating;
import net.skideo.annotations.UnitTest;
import net.skideo.repository.LikeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@UnitTest
public class LikeServiceImplTest {

    @InjectMocks
    private LikeServiceImpl likeService;
    @Mock
    private LikeRepository likeRepository;

    @Test
    public void contextLoads() {
        assertNotNull(likeService);
        assertNotNull(likeRepository);
    }

    @Test
    public void givenFindId_whenCallFindById_thenReturnEquals() {
        final long id = 1;
        Like like = new Like();
        like.setId(id);
        given(likeRepository.findById(id)).willReturn(Optional.of(like));

        Like l = likeService.findById(id);

        assertEquals(l, like);
        verify(likeRepository, times(1)).findById(id);
    }

    @Test
    public void givenFindId_whenCallFindId_thenReturnThrow() {
        final long id2 = 2L;
        given(likeRepository.findById(id2)).willThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> likeService.findById(id2));
        verify(likeRepository, times(1)).findById(id2);
    }

    @Test
    public void givenUpdate_whenCallUpdate_thenReturnEquals() {
        final long id1 = 1;

        Like like = new Like();
        like.setId(id1);
        like.setRating(Rating.ONE);
        given(likeRepository.findById(id1)).willReturn(Optional.of(like));

        Like newLike = new Like();
        newLike.setId(id1);
        newLike.setRating(Rating.TWO);

        assertEquals(likeService.updateRating(newLike),newLike);

        verify(likeRepository,times(1)).findById(id1);
    }
}

