package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.AbstractInfoEntity;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="academy")
public class Academy extends AbstractInfoEntity {

    private int numberPlayers;
    @OneToMany(fetch = FetchType.LAZY)
    private List<User> players = new LinkedList<>();

    public Academy(Info info, int numberPlayers) {
        setInfo(info);
        this.numberPlayers = numberPlayers;
    }

}
