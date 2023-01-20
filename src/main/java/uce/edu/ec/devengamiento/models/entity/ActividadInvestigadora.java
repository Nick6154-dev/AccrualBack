package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ACTIVIDAD_INVESTIGADORA")
public class ActividadInvestigadora {
    @Id
    @Column(name = "ID_ACTIVIDAD_INVESTIGADORA", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TIPO_ACTIVIDAD_INVESTIGADORA", length = 256)
    private String tipoActividadInvestigadora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoActividadInvestigadora() {
        return tipoActividadInvestigadora;
    }

    public void setTipoActividadInvestigadora(String tipoActividadInvestigadora) {
        this.tipoActividadInvestigadora = tipoActividadInvestigadora;
    }

}