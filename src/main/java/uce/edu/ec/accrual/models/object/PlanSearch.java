package uce.edu.ec.accrual.models.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanSearch {

    private Long idPerson;

    private String period;

    public PlanSearch() {
    }

    public PlanSearch(Long idPerson, String period) {
        this.idPerson = idPerson;
        this.period = period;
    }

}
