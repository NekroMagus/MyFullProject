package net.skideo.service.scout;

import net.skideo.model.Scout;

import java.util.List;

public interface ScoutService {

    Scout findById(long id);

    void save(Scout scout);

    List<Scout> findAll();
}
