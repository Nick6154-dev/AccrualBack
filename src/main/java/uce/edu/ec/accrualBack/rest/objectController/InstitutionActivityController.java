package uce.edu.ec.accrualBack.rest.objectController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.object.InstitutionActivity;
import uce.edu.ec.accrualBack.service.objectService.interfaces.InstitutionActivityService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.UtilCommonsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/institutionActivity")
public class InstitutionActivityController {

    @Autowired
    private InstitutionActivityService institutionActivityService;

    @Autowired
    private UtilCommonsService utilCommonsService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody InstitutionActivity institutionActivity, BindingResult result) {
        if (result.hasErrors()) {
            return utilCommonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(institutionActivityService.save(institutionActivity));
    }

    @DeleteMapping("/{idInstitution}")
    public ResponseEntity<?> deleteById(@PathVariable Long idInstitution) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(institutionActivityService.deleteById(idInstitution));
    }

    @PutMapping("/{idInstitution}")
    public ResponseEntity<?> update(@Valid @RequestBody InstitutionActivity institutionActivity, BindingResult result,
                                    @PathVariable Long idInstitution) {
        if (result.hasErrors()) {
            return utilCommonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(institutionActivityService.update(institutionActivity, idInstitution));
    }

}
