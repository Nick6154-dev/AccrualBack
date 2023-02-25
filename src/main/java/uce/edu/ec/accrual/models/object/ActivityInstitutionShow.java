package uce.edu.ec.accrual.models.object;

import lombok.Getter;
import lombok.Setter;
import uce.edu.ec.accrual.models.entity.ActivityPlan;

@Getter
@Setter
public class ActivityInstitutionShow {

    private ActivityPlan activityPlan;

    private Object object;

    public ActivityInstitutionShow(ActivityPlan activityPlan, Object object) {
        this.activityPlan = activityPlan;
        this.object = object;
    }

}
