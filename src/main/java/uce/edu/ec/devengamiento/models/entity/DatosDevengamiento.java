package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "DATOS_DEVENGAMIENTO")
public class DatosDevengamiento {
    @Id
    @Column(name = "ID_DOC_DEV", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ESTADO_DOCENTE", length = 256)
    private String estadoDocente;

    @Column(name = "REDI_CDI")
    private Boolean rediCdi;

    @Column(name = "RNI_SENESYT")
    private Boolean rniSenesyt;

    @Column(name = "FECHA_REINTEGRO")
    private LocalDate fechaReintegro;

    @Column(name = "FECHA_LECTURA_TESIS")
    private LocalDate fechaLecturaTesis;

    @Column(name = "TIENE_ORCID")
    private Boolean tieneOrcid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_RED_SOCIAL", nullable = false)
    private RedSocialInvestigacion idRedSocial;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_DOCENTE", nullable = false)
    private Docente idDocente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstadoDocente() {
        return estadoDocente;
    }

    public void setEstadoDocente(String estadoDocente) {
        this.estadoDocente = estadoDocente;
    }

    public Boolean getRediCdi() {
        return rediCdi;
    }

    public void setRediCdi(Boolean rediCdi) {
        this.rediCdi = rediCdi;
    }

    public Boolean getRniSenesyt() {
        return rniSenesyt;
    }

    public void setRniSenesyt(Boolean rniSenesyt) {
        this.rniSenesyt = rniSenesyt;
    }

    public LocalDate getFechaReintegro() {
        return fechaReintegro;
    }

    public void setFechaReintegro(LocalDate fechaReintegro) {
        this.fechaReintegro = fechaReintegro;
    }

    public LocalDate getFechaLecturaTesis() {
        return fechaLecturaTesis;
    }

    public void setFechaLecturaTesis(LocalDate fechaLecturaTesis) {
        this.fechaLecturaTesis = fechaLecturaTesis;
    }

    public Boolean getTieneOrcid() {
        return tieneOrcid;
    }

    public void setTieneOrcid(Boolean tieneOrcid) {
        this.tieneOrcid = tieneOrcid;
    }

    public RedSocialInvestigacion getIdRedSocial() {
        return idRedSocial;
    }

    public void setIdRedSocial(RedSocialInvestigacion idRedSocial) {
        this.idRedSocial = idRedSocial;
    }

    public Docente getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Docente idDocente) {
        this.idDocente = idDocente;
    }

}