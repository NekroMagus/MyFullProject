package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.AbstractLocationEntity;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "city")
public class City extends AbstractLocationEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Country country;

    public City(String name, Country country) {
        setName(name);
        setCountry(country);
    }

}

     