package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TIPO_ACTIVIDAD_DOCENTE")
public class TipoActividadDocente {
    @Id
    @Column(name = "ID_TIPO_ACTI_DOC", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_TIPO_ACTIVIDAD", nullable = false)
    private TipoActividad idTipoActividad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ACTIV_DOCE", nullable = false)
    private ActividadDocente idActivDoce;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoActividad getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(TipoActividad idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public ActividadDocente getIdActivDoce() {
        return idActivDoce;
    }

    public void setIdActivDoce(ActividadDocente idActivDoce) {
        this.idActivDoce = idActivDoce;
    }

}