package net.skideo.repository;

import net.skideo.dto.ClubProfileDto;
import net.skideo.dto.projections.ClubProfileProjection;
import net.skideo.dto.projections.PasswordProjection;
import net.skideo.exception.ClubNotFoundException;
import net.skideo.model.Club;
import net.skideo.JpaTest;
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
        Club club = repository.findByInfoLogin(LOGIN).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );

        assertNotNull(club);
    }

    @Test
    public void givenValidLogin_whenFindProfileByLogin_thenEquals() {
        final String TITLE_CLUB = "title";
        final String LOGO_LINK = "link";

        ClubProfileDto dto = repository.findInfoProfileByInfoLogin(LOGIN).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );

        assertEquals(dto.getLogoLink(),LOGO_LINK);
        assertEquals(dto.getTitleClub(),TITLE_CLUB);
    }

    @Test
    public void givenInvalidLogin_whenExistsByLogin_thenTrue() {
        boolean isExist = repository.existsByInfoLogin(LOGIN);

        assertTrue(isExist);
    }

    @Test
    public void givenInvalidIdAndClub_whenSaveScriptAndFindById_thenEquals() {
        Club newClub = new Club();
        newClub.setId(ID);
        newClub.getInfo().setLogin("egor");

        repository.save(newClub);

        Club club = repository.findById(ID).orElseThrow(
                () -> new ClubNotFoundException("Club not found")
        );

        assertEquals(club.getInfo().getLogin(), "egor");
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
