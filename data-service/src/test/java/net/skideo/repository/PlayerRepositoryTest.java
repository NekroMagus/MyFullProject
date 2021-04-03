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



}