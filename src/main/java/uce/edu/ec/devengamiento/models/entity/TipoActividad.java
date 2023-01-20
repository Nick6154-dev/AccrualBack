package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TIPO_ACTIVIDAD")
public class TipoActividad {
    @Id
    @Column(name = "ID_TIPO_ACTIVIDAD", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE_TIPO_ACTIVIDAD", length = 256)
    private String nombreTipoActividad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreTipoActividad() {
        return nombreTipoActividad;
    }

    public void setNombreTipoActividad(String nombreTipoActividad) {
        this.nombreTipoActividad = nombreTipoActividad;
    }

}