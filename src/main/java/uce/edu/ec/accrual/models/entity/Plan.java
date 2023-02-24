package uce.edu.ec.accrual.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "plan")
@Getter
@Setter
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan")
    private Long idPlan;

    @Column(name = "fecha_inicio")
    private LocalDate starDate;

    @Column(name = "numero_plan")
    private Integer numberPlan;

    @Column(name = "id_docente")
    private Long idDocent;

    @NotBlank
    @Column(name = "periodo")
    private String period;

    @NotNull
    @Column(name = "editable")
    private Boolean editable;

}
