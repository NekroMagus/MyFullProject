package net.skideo;

import org.junit.Assert;
import net.skideo.dao.ScoutDao;
import net.skideo.model.Scout;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ScoutServiceTests {

    @Autowired
    private ScoutDao scoutDao;

    @Test
    public void saveTest() {
        Scout scout = new Scout();
        scout.setLogin("apache");
        scout.setPassword("1234");
        scout.setName("Egor");
        scout.setSurname("odin");

        scoutDao.save(scout);


    }
}
