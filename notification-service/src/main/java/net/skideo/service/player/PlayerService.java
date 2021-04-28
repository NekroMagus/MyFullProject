package net.skideo.service.player;

import net.skideo.model.Player;

public interface PlayerService {

    Player getPlayerById(long idUser);

    void save(Player player);

    Player getCurrentPlayer();

}
