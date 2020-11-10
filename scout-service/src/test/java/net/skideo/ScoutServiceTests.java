package net.skideo;

import net.skideo.repository.ScoutRepository;
import net.skideo.model.Scout;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ScoutServiceTests {

    @Autowired
    private ScoutRepository scoutRepository;

    @Test
    public void saveTest() {
        Scout scout = new Scout();
        scout.setLogin("apache");
        scout.setPassword("1234");
        scout.setName("Egor");
        scout.setSurname("odin");

        scoutRepository.save(scout);


    }
}
