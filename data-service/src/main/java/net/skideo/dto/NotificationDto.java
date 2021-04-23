package net.skideo.dto;

import lombok.Data;
import net.skideo.model.enums.NotificationEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NotificationDto {

    @NotBlank
    private String message;
    @NotNull
    private NotificationEnum notificationType;
    @NotNull
    private long idUser;

}
