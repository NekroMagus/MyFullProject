package net.skideo.service.auth;

import net.skideo.annotations.UnitTest;
import net.skideo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@UnitTest
public class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private UserRepository userRepository;
    @Spy
    private PasswordEncoder passwordEncoder;


    @Test
    public void contextLoads() {
        assertNotNull(authService);
        assertNotNull(passwordEncoder);
        assertNotNull(userRepository);
    }

    @Test
    public void givenNotExistsLogin_whenCallExistsByLogin_thenReturnFalse() {
        //given
        final String login = "Not exists login";
        given(userRepository.existsByInfoLogin(login)).willReturn(false);

        //when
        boolean userExists = authService.isUserExists(login);

        //then
        assertFalse(userExists);
        verify(userRepository, times(1)).existsByInfoLogin(login);
    }

    @Test
    public void givenExistsLogin_whenCallExistsByLogin_thenReturnTrue() {
        final String login = "Exists login";
        given(userRepository.existsByInfoLogin(login)).willReturn(true);

        boolean userExists = authService.isUserExists(login);

        assertTrue(userExists);
        verify(userRepository, times(1)).existsByInfoLogin(login);
    }


    @Test
    public void givenLogins_whenCallExistsByLogin_thenCalledNeverWithAnotherLogin() {
        final String login = "Exists login";
        final String anotherLogin = "Another login";
        given(userRepository.existsByInfoLogin(login)).willReturn(true);

        authService.isUserExists(login);

        verify(userRepository, never()).existsByInfoLogin(anotherLogin);
    }

    @Test
    public void givenMatchPassword_whenCallIsPasswordMatch_thenReturnTrue() {
        final String rawPassword = "Random";
        final String encodePassword = "$2y$10$cJNc8MCO6RXNzmD4XTjgXe7AjbKa8IDcd7Eksnj/glMKLotPNAbtm";
        given(passwordEncoder.matches(rawPassword, encodePassword)).willReturn(true);

        boolean isMatch = authService.isPasswordMatch(rawPassword, encodePassword);

        assertTrue(isMatch);
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
    }

    @Test
    public void givenMismatchPasswords_whenCallIsPasswordMatch_thenReturnFalse() {
        final String rawPassword = "Random";
        final String mismatchPassword = "Mismatch";
        given(passwordEncoder.matches(rawPassword, mismatchPassword)).willReturn(false);

        boolean isMatch = authService.isPasswordMatch(rawPassword, mismatchPassword);

        assertFalse(isMatch);
   }


}