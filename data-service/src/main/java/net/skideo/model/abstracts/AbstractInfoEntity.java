package net.skideo.model.abstracts;

import lombok.Data;
import net.skideo.model.Info;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@Data
@MappedSuperclass
public abstract class AbstractInfoEntity extends BaseEntity {

    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private Info info;

}
