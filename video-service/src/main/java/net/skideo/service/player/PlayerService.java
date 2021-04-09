package net.skideo.service.player;

import net.skideo.model.Player;

import java.util.List;

public interface PlayerService {

    Player findById(long id);

    List<Player> findAll();

}
