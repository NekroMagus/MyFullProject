package net.skideo.dto;

import net.skideo.dto.projections.ScoutProjection;
import net.skideo.model.Scout;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.enums.Region;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoutDto {

    private String name;
    private String surname;
    private Region region;

    public ScoutDto(ScoutProjection scoutProjection) {
        this.name=scoutProjection.getName();
        this.surname=scoutProjection.getSurname();
    }

    public ScoutDto(Scout scout) {
        this.region=scout.getRegion();
        this.name=scout.getUser().getName();
        this.surname=scout.getUser().getSurname();
    }
}
