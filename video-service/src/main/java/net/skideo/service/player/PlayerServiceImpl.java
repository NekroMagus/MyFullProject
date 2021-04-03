package net.skideo.service.player;

import lombok.RequiredArgsConstructor;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Player;
import net.skideo.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player findById(long id) {
        return playerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }
}
