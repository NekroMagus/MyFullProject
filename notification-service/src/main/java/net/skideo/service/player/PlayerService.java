package net.skideo.service.player;

import net.skideo.model.Player;

public interface PlayerService {

    Player getUserById(long idUser);

    void save(Player player);

    Player getCurrentPlayer();

}
