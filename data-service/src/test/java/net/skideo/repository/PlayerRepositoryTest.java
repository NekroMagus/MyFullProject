package net.skideo.repository;

import net.skideo.model.Player;
import net.skideo.JpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JpaTest
public class PlayerRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private final String USER_LOGIN = "test";

    @Test
    public void givenScript_whenFindByLogin_thenFoundUser() {
        Player player = userRepository.findByInfoLogin(USER_LOGIN).orElse(null);

        assertNotNull(player);
        assertEquals(USER_LOGIN, player.getInfo().getLogin());
    }

    @Test
    public void givenInvalidLogin_whenFindByLogin_thenNotFound() {
        Player player = userRepository.findByInfoLogin("INVALID LOGIN").orElse(null);

        assertNull(player);
    }

    @Test
    public void givenScriptWithId_whenFindById_thenFoundUser() {
        final Long id = 1L;

        Player player = userRepository.findById(id).orElse(null);

        assertNotNull(player);
        assertThat(player.getId()).isEqualTo(id);
    }

    @Test
    public void givenInvalidId_whenFindById_thenNotFound() {
        final Long id = 10000000000L;

        Player player = userRepository.findById(id).orElse(null);

        assertNull(player);
    }

}