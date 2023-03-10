package uce.edu.ec.accrual.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "actividad_plan")
@Getter
@Setter
public class ActivityPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad_plan")
    private Long idActivityPlan;

    @Column(name = "id_plan")
    private Long idPlan;

    @NotNull
    @Column(name = "estado")
    private Integer state;

    @OneToOne
    @JoinColumn(name = "id_actividad")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Activity activity;

    @OneToOne
    @JoinColumn(name = "id_tipo_actividad")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Type type;

    @OneToOne
    @JoinColumn(name = "id_subtipo_actividad")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Subtype subtype;

}
