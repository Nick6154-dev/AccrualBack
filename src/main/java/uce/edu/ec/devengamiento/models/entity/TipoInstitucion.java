package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "tipo_institucion")
public class TipoInstitucion {
    @Id
    @Column(name = "id_tipo_institucion", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_institucion")
    private String nombreInstitucion;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_actividad_devengamiento")
    private PlanDevengamiento idPlanDevengamiento;
    private Integer idActividad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public PlanDevengamiento getIdPlanDevengamiento() {
        return idPlanDevengamiento;
    }

    public void setIdPlanDevengamiento(PlanDevengamiento idPlanDevengamiento) {
        this.idPlanDevengamiento = idPlanDevengamiento;
    }
}