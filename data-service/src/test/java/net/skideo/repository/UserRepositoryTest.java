package net.skideo.repository;

import net.skideo.dto.projections.UserProjection;
import net.skideo.model.User;
import net.skideo.JpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private final String USER_LOGIN = "test";

    @Test
    public void givenScript_whenFindByLogin_thenFoundUser() {
        UserProjection user = userRepository.findByInfoLogin(USER_LOGIN).orElse(null);

        assertNotNull(user);
        assertEquals(USER_LOGIN, user.getInfoLogin());
    }

    @Test
    public void givenInvalidLogin_whenFindByLogin_thenNotFound() {
        UserProjection user = userRepository.findByInfoLogin("INVALID LOGIN").orElse(null);

        assertNull(user);
    }

    @Test
    public void givenScriptWithId_whenFindById_thenFoundUser() {
        final Long id = 1L;

        User user = userRepository.findById(id).orElse(null);

        assertNotNull(user);
        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    public void givenInvalidId_whenFindById_thenNotFound() {
        final Long id = 10000000000L;

        User user = userRepository.findById(id).orElse(null);

        assertNull(user);
    }

}