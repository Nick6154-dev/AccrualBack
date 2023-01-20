package uce.edu.ec.devengamiento.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CODIGO_ORCID")
public class CodigoOrcid {
    @Id
    @Column(name = "ID_CODIGO_ORCID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMERO_ORCID", length = 256)
    private String numeroOrcid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_DOCENTE_DEVENGAMIENTO", nullable = false)
    private DatosDevengamiento idDocenteDevengamiento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroOrcid() {
        return numeroOrcid;
    }

    public void setNumeroOrcid(String numeroOrcid) {
        this.numeroOrcid = numeroOrcid;
    }

    public DatosDevengamiento getIdDocenteDevengamiento() {
        return idDocenteDevengamiento;
    }

    public void setIdDocenteDevengamiento(DatosDevengamiento idDocenteDevengamiento) {
        this.idDocenteDevengamiento = idDocenteDevengamiento;
    }

}