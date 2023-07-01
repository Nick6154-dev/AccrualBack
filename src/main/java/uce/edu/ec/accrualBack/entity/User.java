package uce.edu.ec.accrualBack.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id_usuario", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "id_persona")
    private Long idPerson;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Role> roles;

    public User() {
    }

    public User(String username, String password, Long idPerson, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.idPerson = idPerson;
        this.roles = roles;
    }
}