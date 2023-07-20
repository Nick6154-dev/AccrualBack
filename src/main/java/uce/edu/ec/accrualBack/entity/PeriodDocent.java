package uce.edu.ec.accrualBack.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "periodo_docente")
public class PeriodDocent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_periodo_docente")
    private Long idPeriodDocent;

    @NotNull
    @Column(name = "id_period")
    private Long idPeriod;

    @NotNull
    @Column(name = "id_docente")
    private Long idDocent;

    @NotNull
    @Column(name = "estado")
    private Integer state;

}
