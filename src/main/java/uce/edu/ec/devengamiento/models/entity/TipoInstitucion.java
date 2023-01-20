package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TIPO_INSTITUCION")
public class TipoInstitucion {
    @Id
    @Column(name = "ID_TIPO_INSTITUCION", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE_INSTITUCION", length = 256)
    private String nombreInstitucion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ACTIVIDAD", nullable = false)
    private ActividadDevengamiento idActividad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public ActividadDevengamiento getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(ActividadDevengamiento idActividad) {
        this.idActividad = idActividad;
    }

}