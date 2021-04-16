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

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<User> user = new LinkedList<>();

    public User getUser() {
        return user.get(0);
    }

    public void setUser(User user) {
        if(this.user.isEmpty()) {
            this.user.add(user);
        }
        else {
            this.user.set(0, user);
        }
    }

}
