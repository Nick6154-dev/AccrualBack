package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "universidad_institucion")
@Getter
@Setter
public class UniversidadInstitucion {

    @Id
    @Column(name = "id_universidad_institucion", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_institucion")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipoInstitucion idTipoInstitucion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_universidad")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Universidad idUniversidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facultad")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Facultad idFacultad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Carrera idCarrera;

}