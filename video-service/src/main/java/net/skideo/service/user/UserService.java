package net.skideo.service.user;

import net.skideo.model.Player;

import java.util.List;

public interface UserService {

    Player findById(long id);

    List<Player> findAll();

}
