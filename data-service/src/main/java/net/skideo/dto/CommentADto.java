package net.skideo.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CommentADto {

    // comment id or video id
    @NotNull
    private long id;
    @NotBlank
    @Size(max=200)
    private String text;

}
