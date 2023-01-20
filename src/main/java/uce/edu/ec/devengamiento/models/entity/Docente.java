package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DOCENTE")
public class Docente {
    @Id
    @Column(name = "ID_DOCENTE", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE", length = 256)
    private String nombre;

    @Column(name = "APELLIDOS", length = 256)
    private String apellidos;

    @Column(name = "CEDULA", length = 256)
    private String cedula;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario idUsuario;

    public Docente() {
    }

    public Docente(String nombre, String apellidos, String cedula, Usuario idUsuario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.idUsuario = idUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

}