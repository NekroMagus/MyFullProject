package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.enums.Role;
import net.skideo.model.enums.ServiceRole;

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
    private ServiceRole serviceRole;

    public Auth(String login,String password) {
        this.login=login;
        this.password=password;
    }

    public Auth(String login,String password,ServiceRole serviceRole) {
        this.login=login;
        this.password=password;
        this.serviceRole=serviceRole;
        this.role=Role.UNCONFIRMED;
    }

}
