package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.enums.Role;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="auth")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    private Role role;

    public Auth(String login,String password) {
        this.login=login;
        this.password=password;
    }

}
