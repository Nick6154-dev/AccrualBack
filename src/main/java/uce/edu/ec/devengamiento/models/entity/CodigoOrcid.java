package uce.edu.ec.devengamiento.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "codigo_orcid")
public class CodigoOrcid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_codigo_orcid", nullable = false)
    private Integer id;

    @Column(name = "codigo_orcid")
    private String codigoOrcid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoOrcid() {
        return codigoOrcid;
    }

    public void setCodigoOrcid(String codigoOrcid) {
        this.codigoOrcid = codigoOrcid;
    }

}