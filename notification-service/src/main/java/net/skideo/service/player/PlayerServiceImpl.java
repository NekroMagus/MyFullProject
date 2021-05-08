package net.skideo.service.player;

import lombok.RequiredArgsConstructor;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Player;
import net.skideo.repository.PlayerRepository;
import net.skideo.util.SecurityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Player getPlayerById(long idUser) {
        return playerRepository.findById(idUser).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }

    @Override
    public Player getCurrentPlayer() {
        return findUserByLogin(SecurityUtils.getLogin());
    }

    private Player findUserByLogin(String login) {
        return playerRepository.findByUserLogin(login).orElseThrow(
                () -> new NotFoundException("User not found")
        );
    }

}
