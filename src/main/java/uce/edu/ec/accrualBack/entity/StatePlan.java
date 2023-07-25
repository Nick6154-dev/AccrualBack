package uce.edu.ec.accrualBack.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "estado_plan")
public class StatePlan {

    @Id
    private String idStatePlan;

    private Long idPlan;

    private Integer statePeriod;

    private Integer statePlan;

    public StatePlan() {
    }

    public StatePlan(Long idPlan, Integer statePeriod, Integer statePlan) {
        this.idPlan = idPlan;
        this.statePeriod = statePeriod;
        this.statePlan = statePlan;
    }

}
