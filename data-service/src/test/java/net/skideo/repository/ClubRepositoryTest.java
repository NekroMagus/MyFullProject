package net.skideo.repository;

import net.skideo.JpaTest;
import net.skideo.dto.projections.ClubPasswordProjection;
import net.skideo.dto.projections.ClubProfileProjection;
import net.skideo.exception.ClubNotFoundException;
import net.skideo.model.Club;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

@JpaTest
public class ClubRepositoryTest {

    @Autowired
    private ClubRepository repository;
    private final String LOGIN = "apache";
    private final long ID = 1L;

    @Test
    public void contextLoads() {
        assertNotNull(repository);
    }

    @Test
    public void givenValidId_whenFindById_thenFoundClub() {
        Club club = repository.findById(ID).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );

        assertNotNull(club);
    }

    @Test
    public void givenInvalidId_whenFindById_thenNotFoundClub() {
        final long ID = 10000L;

        assertThrows(ClubNotFoundException.class,() -> repository.findById(ID).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        ));
    }

    @Test
    public void givenValidLogin_whenFindByLogin_thenFoundClub() {
        Club club = repository.findByLogin(LOGIN).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );

        assertNotNull(club);
    }

    @Test
    public void givenValidLogin_whenFindPasswordProjectionByLogin_thenEquals() {
        final String PASSWORD = "password";

        ClubPasswordProjection projection = repository.findPasswordByLogin(LOGIN).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );

        assertEquals(projection.getPassword(),PASSWORD);
    }

    @Test
    public void givenValidLogin_whenFindProfileByLogin_thenEquals() {
        final String TITLE_CLUB = "title";
        final String LOGO_LINK = "link";

        ClubProfileProjection projection = repository.findProfileByLogin(LOGIN).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );

        assertEquals(projection.getLogoLink(),LOGO_LINK);
        assertEquals(projection.getTitleClub(),TITLE_CLUB);
    }

    @Test
    public void givenInvalidLogin_whenExistsByLogin_thenTrue() {
        boolean isExist = repository.existsByLogin(LOGIN);

        assertTrue(isExist);
    }

    @Test
    public void givenInvalidIdAndClub_whenSaveScriptAndFindById_thenEquals() {
        Club newClub = new Club();
        newClub.setId(ID);
        newClub.setLogin("egor");

        repository.save(newClub);

        Club club = repository.findById(ID).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );

        assertEquals(club.getLogin(), "egor");
    }

    @Test
    public void givenInvalidId_whenDeleteScriptAndFindById_thenNotFoundClub() {
        repository.deleteById(ID);

        assertThrows(ClubNotFoundException.class,() ->
            repository.findById(ID).orElseThrow(
                    () -> new ClubNotFoundException("Club not found")
            )
        );
    }

}
