package uce.edu.ec.devengamiento.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "codigo_orcid")
@Getter
@Setter
public class CodigoOrcid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_codigo_orcid", nullable = false)
    private Long id;

    @Column(name = "codigo_orcid")
    private String codigoOrcid;

}