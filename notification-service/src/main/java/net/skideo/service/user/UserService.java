package net.skideo.service.user;

import net.skideo.model.Player;

public interface UserService {

    Player getUserById(long idUser);

    void save(Player player);

    Player getCurrentUser();

}
