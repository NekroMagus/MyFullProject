package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileDto {

    @NotBlank
    @Size(min=2)
    private String name;
    @NotBlank
    @Size(min=2)
    private String surname;

}
