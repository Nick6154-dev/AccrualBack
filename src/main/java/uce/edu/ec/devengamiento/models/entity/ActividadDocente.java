package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ACTIVIDAD_DOCENTE")
public class ActividadDocente {
    @Id
    @Column(name = "ID_ACTIVIDAD_DOCENTE", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TIPO_ACTIVIDAD_DOCENTE", length = 256)
    private String tipoActividadDocente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoActividadDocente() {
        return tipoActividadDocente;
    }

    public void setTipoActividadDocente(String tipoActividadDocente) {
        this.tipoActividadDocente = tipoActividadDocente;
    }

}