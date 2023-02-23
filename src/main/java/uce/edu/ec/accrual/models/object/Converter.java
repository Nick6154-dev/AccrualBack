package uce.edu.ec.accrual.models.object;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class Converter {

    private Long idPlan;

    @NotBlank
    private String descriptionActivity;

    @NotNull
    private LocalDate starDate;

    @NotNull
    private LocalDate endDate;

    @NotBlank
    private String evidences;

    @NotNull
    private Long idActivityType;

    @NotNull
    private Long idActivitySubtype;

    @NotBlank
    private String descriptionSubtype;

    private Long idActivity;

    @NotBlank
    private String institutionName;

    @NotBlank
    private String otherInstitutionName;

    @NotBlank
    private String verificationLink;

    @NotNull
    private Long idUniversity;

    @NotNull
    private Long idFaculty;

    @NotNull
    private Long idCareer;

    @NotNull
    private Long idPerson;

    @NotBlank
    private String period;

    public Converter() {
    }

    public Converter(Long idPlan, String descriptionActivity, LocalDate starDate, LocalDate endDate, String evidences,
                     Long idActivityType, Long idActivitySubtype, String descriptionSubtype, Long idActivity,
                     String institutionName, String otherInstitutionName, String verificationLink, Long idUniversity,
                     Long idFaculty, Long idCareer, Long idPerson, String period) {
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
        this.period = period;
    }
}
