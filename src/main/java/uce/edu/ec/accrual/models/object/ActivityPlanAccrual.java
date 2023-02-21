package uce.edu.ec.accrual.models.object;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ActivityPlanAccrual {

    @NotNull
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

}
