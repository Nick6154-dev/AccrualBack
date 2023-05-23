package uce.edu.ec.accrual.models.object;

import lombok.Getter;
import lombok.Setter;
import uce.edu.ec.accrual.models.entity.ActivityPlan;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.entity.Person;
import uce.edu.ec.accrual.models.entity.Plan;

import java.util.List;

@Getter
@Setter
public class ValidatorObject {

    private Person person;

    private Docent docent;

    private List<Plan> plans;

    private List<ActivityPlan> activityPlans;

    public ValidatorObject() {
    }

    public ValidatorObject(Person person, Docent docent) {
        this.person = person;
        this.docent = docent;
    }

    public ValidatorObject(Person person, Docent docent, List<Plan> plans) {
        this.person = person;
        this.docent = docent;
        this.plans = plans;
    }

    public ValidatorObject(Person person, Docent docent, List<Plan> plans, List<ActivityPlan> activityPlans) {
        this.person = person;
        this.docent = docent;
        this.plans = plans;
        this.activityPlans = activityPlans;
    }

}
