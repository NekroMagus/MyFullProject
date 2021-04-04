package net.skideo.model;

import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.AbstractLocationEntity;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "country")
public class Country extends AbstractLocationEntity {

    public Country(String country) {
        setName(country);
    }

}
