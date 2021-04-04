package net.skideo.model;

import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.AbstractLocationEntity;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "city")
public class City extends AbstractLocationEntity {

    public City(String city) {
        setName(city);
    }

}
