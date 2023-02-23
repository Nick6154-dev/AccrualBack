package uce.edu.ec.accrual.models.object;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ActivityInstitutionJoin {

    @NotNull
    private Long idPerson;

    @NotBlank
    private String period;

    @NotNull
    private ActivityPlanAccrual activityPlanAccrual;

    @NotNull
    private InstitutionActivity institutionActivity;

    public ActivityInstitutionJoin() {
    }

    public ActivityInstitutionJoin(Long idPerson, String period, ActivityPlanAccrual activityPlanAccrual, InstitutionActivity institutionActivity) {
        this.idPerson = idPerson;
        this.period = period;
        this.activityPlanAccrual = activityPlanAccrual;
        this.institutionActivity = institutionActivity;
    }
}
