package uce.edu.ec.accrualBack.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "datos_devengamiento")
@Getter
@Setter
public class AccrualData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_datos_devengamiento")
    private Long idAccrualData;

    @NotBlank
    @Column(name = "enlace_tesis")
    private String thesisLink;

    @NotNull
    @Column(name = "fecha_lectura_tesis")
    private LocalDate readingThesisDate;

    @NotNull
    @Column(name = "fecha_reintegro")
    private LocalDate refundDate;

    @NotBlank
    @Column(name = "enlace_contrato_adenda")
    private String contractAddendumLink;

    @NotNull
    @Column(name = "finiquito")
    private Boolean settlement;

    @NotBlank
    @Column(name = "observaciones")
    private String observations;

    @NotNull
    @Column(name = "tiempo_devengamiento")
    private Integer accrualTime;

    @OneToOne
    @JoinColumn(name = "id_docente")
    private Docent docent;

}
