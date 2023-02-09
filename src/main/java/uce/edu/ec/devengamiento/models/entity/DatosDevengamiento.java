package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "datos_devengamiento")
@Getter
@Setter
public class DatosDevengamiento {
    @Id
    @Column(name = "id_datos_devengamiento", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "facultad")
    private String facultad;

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

}