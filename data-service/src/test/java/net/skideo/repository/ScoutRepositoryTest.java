package net.skideo.repository;

import net.skideo.exception.ScoutNotFoundException;
import net.skideo.model.Scout;
import net.skideo.JpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

@JpaTest
public class ScoutRepositoryTest {

    @Autowired
    private ScoutRepository repository;
    private final long ID = 1L;
    private final String LOGIN = "apache";

    @Test
    public void contextLoad() {
        assertNotNull(repository);
    }

    @Test
    public void givenValidId_whenFindById_thenNotNull() {
        Scout scout = repository.findById(ID).orElseThrow(
                () -> new ScoutNotFoundException("Scout not found")
        );

        assertNotNull(scout);
    }




    @Test
    public void givenClubId_whenFindAllByClubId_then() {
        final long CLUB_ID = 1L;
    }

    @Test
    public void givenValidId_whenDeleteByIdAndFindById_thenNotFound() {
        repository.deleteById(ID);

        assertThrows(ScoutNotFoundException.class,() -> repository.findById(ID).orElseThrow(
                () -> new ScoutNotFoundException("Scout not found")
        ));
    }



}
