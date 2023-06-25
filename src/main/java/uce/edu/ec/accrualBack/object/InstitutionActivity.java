package uce.edu.ec.accrualBack.object;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class InstitutionActivity {

    @NotNull
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

}
