package uce.edu.ec.accrualBack.entity;

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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
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

    public ActivityPlan() {
    }

    public ActivityPlan(Long idPlan, Integer state, Activity activity, Type type, Subtype subtype) {
        this.idPlan = idPlan;
        this.state = state;
        this.activity = activity;
        this.type = type;
        this.subtype = subtype;
    }
}
