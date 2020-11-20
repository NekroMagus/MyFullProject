package net.skideo.service.like;

import net.skideo.annotations.UnitTest;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Like;
import net.skideo.model.User;
import net.skideo.repository.LikeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
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

        assertEquals(l,like);
        verify(likeRepository,times(1)).findById(id);
    }

    @Test
    public void givenFindId_whenCallFindId_thenReturnThrow() {
        final long id = 1L;
        Like like = new Like();
        like.setId(id);
        given(likeRepository.findById(id)).willReturn(Optional.of(like));

        Like l = likeService.findById(2L);
        ///assertThrows(NotFoundException.class,l);

        verify(likeRepository,times(1)).findById(id);
    }
}
