package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "USUARIO")
public class Usuario {
    @Id
    @Column(name = "ID_USUARIO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USUARIO", length = 256)
    private String usuario;

    @Column(name = "PASSWORD", length = 256)
    private String password;

    public Usuario() {
    }

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}