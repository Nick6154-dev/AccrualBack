package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrualBack.service.entityService.interfaces.ActivityPlanService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DescriptionService;

@RestController
@RequestMapping("/description")
public class DescriptionController {

    @Autowired
    private DescriptionService descriptionService;

    @Autowired
    private ActivityPlanService activityPlanService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(descriptionService.findAll());
    }

    @GetMapping("/{idDescription}")
    public ResponseEntity<?> findById(@PathVariable Long idDescription) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(descriptionService.findById(idDescription));
    }

    @GetMapping("/byActivityPlan/{idActivityPlan}")
    public ResponseEntity<?> findDescriptionByIdActivityPlan(@PathVariable Long idActivityPlan) {
        if (activityPlanService.findById(idActivityPlan) != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(descriptionService.findDescriptionByActivityPlan(activityPlanService.findById(idActivityPlan)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id especificado no existe en el sistema");
    }

}
