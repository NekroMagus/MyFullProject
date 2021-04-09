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

    @ManyToOne
    private Country country;

    public City(String city,Country country) {
        setName(city);
        setCountry(country);
    }

}
