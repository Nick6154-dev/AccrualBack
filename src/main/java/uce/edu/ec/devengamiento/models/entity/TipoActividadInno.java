package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TIPO_ACTIVIDAD_INNO")
public class TipoActividadInno {
    @Id
    @Column(name = "ID_INNO_TIPO_ACTI", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "INN_ID_INN", nullable = false)
    private Innovacion innIdInn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_TIPO_ACTIVIDAD", nullable = false)
    private TipoActividad idTipoActividad;

    public TipoActividadInno() {
    }

    public TipoActividadInno(Innovacion innIdInn, TipoActividad idTipoActividad) {
        this.innIdInn = innIdInn;
        this.idTipoActividad = idTipoActividad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Innovacion getInnIdInn() {
        return innIdInn;
    }

    public void setInnIdInn(Innovacion innIdInn) {
        this.innIdInn = innIdInn;
    }

    public TipoActividad getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(TipoActividad idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

}