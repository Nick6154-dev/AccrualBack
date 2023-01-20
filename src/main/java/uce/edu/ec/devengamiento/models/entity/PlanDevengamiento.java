package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "PLAN_DEVENGAMIENTO")
public class PlanDevengamiento {
    @Id
    @Column(name = "ID_PLAN", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FECHA_INICIO")
    private LocalDate fechaInicio;

    @Column(name = "PERIODO", length = 256)
    private String periodo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_DOCENTE", nullable = false)
    private Docente idDocente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Docente getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Docente idDocente) {
        this.idDocente = idDocente;
    }

}