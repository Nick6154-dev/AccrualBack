package uce.edu.ec.devengamiento.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "plan_devengamiento")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlanDevengamiento {
    @Id
    @Column(name = "id_plan_devengamiento", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_plan")
    private Integer numeroPlan;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "periodo")
    private String periodo;

    @Column(name = "estado_plan")
    private boolean estadoPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_docente")
    private Docente idDocente;

}