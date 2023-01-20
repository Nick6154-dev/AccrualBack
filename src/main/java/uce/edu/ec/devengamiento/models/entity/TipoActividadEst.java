package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TIPO_ACTIVIDAD_EST")
public class TipoActividadEst {
    @Id
    @Column(name = "ID_TIPO_ACT_ESCTID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_TIPO_ACTIVIDAD", nullable = false)
    private TipoActividad idTipoActividad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ESTRUCTURA", nullable = false)
    private EstructurasID idEstructura;

    public TipoActividadEst() {
    }

    public TipoActividadEst(TipoActividad idTipoActividad, EstructurasID idEstructura) {
        this.idTipoActividad = idTipoActividad;
        this.idEstructura = idEstructura;
    }

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

    public EstructurasID getIdEstructura() {
        return idEstructura;
    }

    public void setIdEstructura(EstructurasID idEstructura) {
        this.idEstructura = idEstructura;
    }

}