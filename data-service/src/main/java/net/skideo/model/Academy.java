package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="academy")
public class Academy extends BaseEntity {

    private int numberPlayers;
    @OneToMany(fetch = FetchType.LAZY)
    private List<User> players = new LinkedList<>();
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private Info info;


    public Academy(Info info, int numberPlayers) {
        this.info=info;
        this.numberPlayers = numberPlayers;
    }

    @Override
    public String toString() {
        return "created: " + getCreated();
    }

}
