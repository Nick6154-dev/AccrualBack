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

    public ActivityInstitutionJoin(Converter converter) {
        this.idPerson = converter.getIdPerson();
        this.period = converter.getPeriod();
        this.activityPlanAccrual = convertactivityPlanAccrual(converter);
        this.institutionActivity = convertInstitutionActivity(converter);
    }

    private ActivityPlanAccrual convertactivityPlanAccrual(Converter converter) {
        ActivityPlanAccrual planAccrual = new ActivityPlanAccrual();
        planAccrual.setIdPlan(converter.getIdPlan());
        planAccrual.setStarDate(converter.getStarDate());
        planAccrual.setEvidences(converter.getEvidences());
        planAccrual.setEndDate(converter.getEndDate());
        planAccrual.setIdActivityType(converter.getIdActivityType());
        planAccrual.setDescriptionSubtype(converter.getDescriptionSubtype());
        planAccrual.setIdActivitySubtype(converter.getIdActivitySubtype());
        planAccrual.setDescriptionActivity(converter.getDescriptionActivity());
        return planAccrual;
    }

    private InstitutionActivity convertInstitutionActivity(Converter converter) {
        InstitutionActivity activity = new InstitutionActivity();
        activity.setIdActivity(converter.getIdActivity());
        activity.setInstitutionName(converter.getInstitutionName());
        activity.setVerificationLink(converter.getVerificationLink());
        activity.setIdFaculty(converter.getIdFaculty());
        activity.setIdUniversity(converter.getIdUniversity());
        activity.setOtherInstitutionName(converter.getInstitutionName());
        activity.setIdCareer(converter.getIdCareer());
        return activity;
    }

}
