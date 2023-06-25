package uce.edu.ec.accrualBack.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @NotNull
    @Column(name = "editable")
    private Boolean editable;

    @NotNull
    @Column(name = "estado")
    private Integer state;

    @OneToOne
    @JoinColumn(name = "id_periodo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Period period;

    @NotBlank
    @Column(name = "observaciones")
    private String observations;

}
