package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CARRERA")
public class Carrera {
    @Id
    @Column(name = "ID_CARRERA", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE_CARRERA", length = 256)
    private String nombreCarrera;

    @Column(name = "ID_FACULTAD", nullable = false)
    private Long idFacultad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public Long getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Long idFacultad) {
        this.idFacultad = idFacultad;
    }

}