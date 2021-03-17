package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademyVideoDto {

    @NotBlank
    @Size(min=8)
    private String link;
    @NotBlank
    @Size(min=30,max=250)
    private String description;

}
