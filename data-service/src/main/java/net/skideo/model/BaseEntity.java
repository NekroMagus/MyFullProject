package net.skideo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity extends AbstractEntity {

    @CreatedDate
    @JsonFormat(pattern = "dd-MM-yyyy--HH-mm-SS")
    private LocalDateTime created;

    @LastModifiedDate
    @JsonFormat(pattern = "dd-MM-yyyy--HH-mm-SS")
    private LocalDateTime updated;

}
