package uce.edu.ec.accrualBack.object;

import lombok.Getter;
import lombok.Setter;
import uce.edu.ec.accrualBack.entity.ActivityPlan;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Person;
import uce.edu.ec.accrualBack.entity.Plan;

import java.util.List;

@Getter
@Setter
public class ValidatorObject {

    private Person person;

    private Docent docent;

    private List<Plan> plans;

    private List<ActivityPlan> activityPlans;

    private String observation;

    public ValidatorObject() {
    }

    public ValidatorObject(Person person) {
        this.person = person;
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
