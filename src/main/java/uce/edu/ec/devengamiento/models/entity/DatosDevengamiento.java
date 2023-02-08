package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "datos_devengamiento")
public class DatosDevengamiento {
    @Id
    @Column(name = "id_datos_devengamiento", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "estado_docente")
    private String estadoDocente;

    @Column(name = "categoria_docente")
    private String categoriaDocente;

    @Column(name = "modalidad")
    private String modalidad;

    @Column(name = "redi_cedia")
    private Boolean rediCedia;

    @Column(name = "rni_senesyt")
    private Boolean rniSenesyt;

    @Column(name = "fecha_reintegro")
    private LocalDate fechaReintegro;

    @Column(name = "fecha_lectura_tesis")
    private LocalDate fechaLecturaTesis;

    @Column(name = "enlace_tesis")
    private String enlaceTesis;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_docente")
    private Docente idDocente;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_codigo_orcid")
    private CodigoOrcid idCodigoOrcid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstadoDocente() {
        return estadoDocente;
    }

    public void setEstadoDocente(String estadoDocente) {
        this.estadoDocente = estadoDocente;
    }

    public Boolean getRediCedia() {
        return rediCedia;
    }

    public void setRediCedia(Boolean rediCedia) {
        this.rediCedia = rediCedia;
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

    public Docente getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Docente idDocente) {
        this.idDocente = idDocente;
    }

    public CodigoOrcid getIdCodigoOrcid() {
        return idCodigoOrcid;
    }

    public void setIdCodigoOrcid(CodigoOrcid idCodigoOrcid) {
        this.idCodigoOrcid = idCodigoOrcid;
    }

}