package uce.edu.ec.accrual.models.object;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Converter {

    private Long idPlan;

    private String descriptionActivity;

    private LocalDate starDate;

    private LocalDate endDate;

    private String evidences;

    private Long idActivityType;

    private Long idActivitySubtype;

    private String descriptionSubtype;

    private Long idActivity;

    private String institutionName;

    private String otherInstitutionName;

    private String verificationLink;

    private Long idUniversity;

    private Long idFaculty;

    private Long idCareer;

    private Long idPerson;

    private Long idPeriod;

    public Converter() {
    }

    public Converter(Long idPlan, String descriptionActivity, LocalDate starDate, LocalDate endDate, String evidences,
                     Long idActivityType, Long idActivitySubtype, String descriptionSubtype, Long idActivity,
                     String institutionName, String otherInstitutionName, String verificationLink, Long idUniversity,
                     Long idFaculty, Long idCareer, Long idPerson, Long idPeriod) {
        this.idPlan = idPlan;
        this.descriptionActivity = descriptionActivity;
        this.starDate = starDate;
        this.endDate = endDate;
        this.evidences = evidences;
        this.idActivityType = idActivityType;
        this.idActivitySubtype = idActivitySubtype;
        this.descriptionSubtype = descriptionSubtype;
        this.idActivity = idActivity;
        this.institutionName = institutionName;
        this.otherInstitutionName = otherInstitutionName;
        this.verificationLink = verificationLink;
        this.idUniversity = idUniversity;
        this.idFaculty = idFaculty;
        this.idCareer = idCareer;
        this.idPerson = idPerson;
        this.idPeriod = idPeriod;
    }
}
