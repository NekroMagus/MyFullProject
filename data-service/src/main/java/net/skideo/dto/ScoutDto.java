package net.skideo.dto;

import net.skideo.dto.projections.ScoutProjection;
import net.skideo.model.Scout;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoutDto {

    private String name;
    private String surname;
    private String region;

    public ScoutDto(ScoutProjection scoutProjection) {
        this.name=scoutProjection.getName();
        this.surname=scoutProjection.getSurname();
    }

    public ScoutDto(Scout scout) {
        this.region=scout.getRegion();
        this.name=scout.getInfo().getName();
        this.surname=scout.getInfo().getSurname();
    }
}
