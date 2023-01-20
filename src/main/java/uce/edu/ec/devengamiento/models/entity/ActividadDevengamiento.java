package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ACTIVIDAD_DEVENGAMIENTO")
public class ActividadDevengamiento {
    @Id
    @Column(name = "ID_ACTIVIDAD", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FECHA_INICIO_ACTIVIDAD")
    private LocalDate fechaInicioActividad;

    @Column(name = "FECHA_FIN_ACTIVIDAD")
    private LocalDate fechaFinActividad;

    @Lob
    @Column(name = "DESCRIPCION_ACTIVIDAD")
    private String descripcionActividad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_TIPO_ACTIVIDAD", nullable = false)
    private TipoActividad idTipoActividad;

    @Column(name = "EVIDENCIAS_LINK", length = 256)
    private String evidenciasLink;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PLAN", nullable = false)
    private PlanDevengamiento idPlan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaInicioActividad() {
        return fechaInicioActividad;
    }

    public void setFechaInicioActividad(LocalDate fechaInicioActividad) {
        this.fechaInicioActividad = fechaInicioActividad;
    }

    public LocalDate getFechaFinActividad() {
        return fechaFinActividad;
    }

    public void setFechaFinActividad(LocalDate fechaFinActividad) {
        this.fechaFinActividad = fechaFinActividad;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public TipoActividad getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(TipoActividad idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public String getEvidenciasLink() {
        return evidenciasLink;
    }

    public void setEvidenciasLink(String evidenciasLink) {
        this.evidenciasLink = evidenciasLink;
    }

    public PlanDevengamiento getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(PlanDevengamiento idPlan) {
        this.idPlan = idPlan;
    }

}