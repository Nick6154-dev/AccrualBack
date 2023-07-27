package uce.edu.ec.accrualBack.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "descripcion_subtipo")
@Getter
@Setter
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descripcion_subtipo")
    private Long idDescriptionSubtype;

    @Column(name = "descripcion")
    private String description;

    @OneToOne
    @JoinColumn(name = "id_actividad_plan")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ActivityPlan activityPlan;

    public Description() {
    }

    public Description(String description, ActivityPlan activityPlan) {
        this.description = description;
        this.activityPlan = activityPlan;
    }
}
