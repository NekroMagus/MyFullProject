package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="academy")
public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int numberPlayers;
    @OneToMany(fetch = FetchType.LAZY)
    private List<User> listPlayers = new LinkedList<>();
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private Info info;


    public Academy(Info info, int numberPlayers) {
        this.info=info;
        this.numberPlayers = numberPlayers;
    }
}
