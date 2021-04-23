package net.skideo.model.abstracts;

import lombok.Data;
import net.skideo.model.User;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Data
@MappedSuperclass
public abstract class AbstractUserEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private User user;

}
