package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ACTIVIDAD_DOCENTE_DETALLE")
public class ActividadDocenteDetalle {
    @Id
    @Column(name = "ID_TIPO_ACT_DET_ACTV", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DET_ACT_ID_DET", nullable = false)
    private DetalleActividad detActIdDet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ACTIVIDAD_DOCENTE", nullable = false)
    private ActividadDocente idActividadDocente;

    public ActividadDocenteDetalle() {
    }

    public ActividadDocenteDetalle(DetalleActividad detActIdDet, ActividadDocente idActividadDocente) {
        this.detActIdDet = detActIdDet;
        this.idActividadDocente = idActividadDocente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetalleActividad getDetActIdDet() {
        return detActIdDet;
    }

    public void setDetActIdDet(DetalleActividad detActIdDet) {
        this.detActIdDet = detActIdDet;
    }

    public ActividadDocente getIdActividadDocente() {
        return idActividadDocente;
    }

    public void setIdActividadDocente(ActividadDocente idActividadDocente) {
        this.idActividadDocente = idActividadDocente;
    }

}