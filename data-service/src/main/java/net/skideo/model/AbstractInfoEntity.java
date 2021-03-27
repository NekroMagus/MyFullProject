package net.skideo.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@Data
@MappedSuperclass
public abstract class AbstractInfoEntity extends BaseEntity {

    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private Info info;

}
