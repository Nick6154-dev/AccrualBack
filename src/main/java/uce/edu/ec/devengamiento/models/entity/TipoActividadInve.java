package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TIPO_ACTIVIDAD_INVES")
public class TipoActividadInve {
    @Id
    @Column(name = "TIPO_ACT_ACTIV_INVES", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ACT_INV", nullable = false)
    private ActividadInvestigadora idActInv;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_TIPO_ACTIVIDAD", nullable = false)
    private TipoActividad idTipoActividad;

    public TipoActividadInve() {
    }

    public TipoActividadInve(ActividadInvestigadora idActInv, TipoActividad idTipoActividad) {
        this.idActInv = idActInv;
        this.idTipoActividad = idTipoActividad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActividadInvestigadora getIdActInv() {
        return idActInv;
    }

    public void setIdActInv(ActividadInvestigadora idActInv) {
        this.idActInv = idActInv;
    }

    public TipoActividad getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(TipoActividad idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

}