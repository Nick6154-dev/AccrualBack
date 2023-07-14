package uce.edu.ec.accrualBack.object;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PlanInstitutionActivity {

    private Long idPlan;

    private String descriptionActivity;

    private LocalDate starDate;

    private LocalDate endDate;

    //Validate
    private String evidences;

    private Long idActivityType;

    private Long idActivitySubtype;

    private String descriptionSubtype;

    private Long idActivity;

    private String institutionName;

    private String otherInstitutionName;

    //Validate
    private String verificationLink;

    private Long idUniversity;

    private Long idFaculty;

    private Long idCareer;

    private Long idPerson;

    private Long idPeriod;

    public PlanInstitutionActivity() {
    }

    //Constructor to validate activities
    public PlanInstitutionActivity(String evidences, String verificationLink) {
        this.evidences = evidences;
        this.verificationLink = verificationLink;
    }

    //Constructor to register activities
    public PlanInstitutionActivity(String descriptionActivity, LocalDate starDate, LocalDate endDate, Long idActivityType,
                                   Long idActivitySubtype, String descriptionSubtype, Long idActivity, String institutionName,
                                   String otherInstitutionName, Long idUniversity, Long idFaculty, Long idCareer, Long idPerson,
                                   Long idPeriod) {
        this.descriptionActivity = descriptionActivity;
        this.starDate = starDate;
        this.endDate = endDate;
        this.idActivityType = idActivityType;
        this.idActivitySubtype = idActivitySubtype;
        this.descriptionSubtype = descriptionSubtype;
        this.idActivity = idActivity;
        this.institutionName = institutionName;
        this.otherInstitutionName = otherInstitutionName;
        this.idUniversity = idUniversity;
        this.idFaculty = idFaculty;
        this.idCareer = idCareer;
        this.idPerson = idPerson;
        this.idPeriod = idPeriod;
    }

}
