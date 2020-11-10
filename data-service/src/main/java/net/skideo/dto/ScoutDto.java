package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Scout;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoutDto {

    private String name;
    private String surname;

    public ScoutDto(Scout scout) {
        this.name=scout.getName();
        this.surname=scout.getSurname();
    }
}
