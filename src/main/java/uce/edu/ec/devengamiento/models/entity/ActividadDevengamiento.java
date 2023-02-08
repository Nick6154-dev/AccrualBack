package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "actividad_devengamiento")
public class ActividadDevengamiento {
    @Id
    @Column(name = "id_actividad_devengamiento", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_inicio_actividad")
    private LocalDate fechaInicioActividad;

    @Column(name = "fecha_fin_actividad")
    private Integer fechaFinActividad;

    @Column(name = "descripcion_actividad")
    private String descripcionActividad;

    @Column(name = "evidencias_link")
    private String evidenciasLink;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan_devengamiento")
    private PlanDevengamiento idPlanDevengamiento;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TipoActividad> tipoActividades;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaInicioActividad() {
        return fechaInicioActividad;
    }

    public void setFechaInicioActividad(LocalDate fechaInicioActividad) {
        this.fechaInicioActividad = fechaInicioActividad;
    }

    public Integer getFechaFinActividad() {
        return fechaFinActividad;
    }

    public void setFechaFinActividad(Integer fechaFinActividad) {
        this.fechaFinActividad = fechaFinActividad;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public String getEvidenciasLink() {
        return evidenciasLink;
    }

    public void setEvidenciasLink(String evidenciasLink) {
        this.evidenciasLink = evidenciasLink;
    }

    public PlanDevengamiento getIdPlanDevengamiento() {
        return idPlanDevengamiento;
    }

    public void setIdPlanDevengamiento(PlanDevengamiento idPlanDevengamiento) {
        this.idPlanDevengamiento = idPlanDevengamiento;
    }

}