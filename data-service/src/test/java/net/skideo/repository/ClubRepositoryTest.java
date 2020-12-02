package net.skideo.repository;

import net.skideo.JpaTest;
import net.skideo.dto.projections.ClubPasswordProjection;
import net.skideo.exception.ClubNotFoundException;
import net.skideo.model.Club;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@JpaTest
public class ClubRepositoryTest {

    @Autowired
    private ClubRepository repository;

    Logger log = Logger.getLogger(ClubRepositoryTest.class.getName());

    @Test
    public void contextLoads() {
        assertNotNull(repository);
    }

    @Test
    public void findByIdTest_NotNull() {
        Club club = repository.findById(1L).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );

        assertNotNull(club);
    }

    @Test
    public void findByIdTest_Throws() {
        assertThrows(ClubNotFoundException.class,() -> repository.findById(2L).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        ));
    }

    @Test
    public void findByLoginTest() {
        Club club = repository.findByLogin("apache").orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );

        assertNotNull(club);
    }
    
    @Test
    public void findPasswordByLoginTest() {
        final String login = "apache";

        ClubPasswordProjection projection = repository.findPasswordByLogin(login).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );

        assertNotNull(projection);
    }

    @Test
    public void updateTest() {
        final long id = 1L;

        Club newClub = new Club();
        newClub.setId(id);
        newClub.setLogin("egor");

        repository.save(newClub);

        Club club = repository.findById(id).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );

        assertEquals(club.getLogin(),"egor");
    }

    @Test
    public void deleteByIdTest() {
        final long id = 1L;

        repository.deleteById(id);
        assertNull(repository.findById(id));
    }

}
