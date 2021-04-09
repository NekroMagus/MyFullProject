package net.skideo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class VideoLinkDto {

    @NotEmpty
    private String link;
}
