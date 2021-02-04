package net.skideo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "skideo_notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private net.skideo.model.enums.Notification notification;

}
