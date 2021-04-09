package net.skideo.model.abstracts;

import lombok.Data;
import net.skideo.model.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@Data
@MappedSuperclass
public abstract class AbstractUserEntity extends BaseEntity {

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private User user;

}
