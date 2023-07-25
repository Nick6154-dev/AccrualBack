package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DocentService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.UtilCommonsService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/docent")
public class DocentController {

    @Autowired
    private DocentService service;

    @Autowired
    private UtilCommonsService commonsService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findAll());
    }

    @GetMapping("/{idDocent}")
    public ResponseEntity<?> findById(@PathVariable Long idDocent) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findById(idDocent));
    }

    @GetMapping("/byIdPerson/{idPerson}")
    public ResponseEntity<?> findByIdPerson(@PathVariable Long idPerson) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findByIdPerson(idPerson));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Docent docent, BindingResult result) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(docent));
    }

    @DeleteMapping("/{idDocent}")
    public ResponseEntity<?> deleteById(@PathVariable Long idDocent) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteById(idDocent));
    }

    @PutMapping("/{idDocent}")
    public ResponseEntity<?> update(@Valid @RequestBody Docent docent, BindingResult result, @PathVariable Long idDocent) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateAll(docent, idDocent));
    }

    @PatchMapping("/updateCategoryModalityDocent/{idPerson}")
    public ResponseEntity<?> updateCategoryModalityDocent(@RequestBody Map<String, String> newValues, @PathVariable Long idPerson) {
        Map<Integer, String> response = service.updateCategoryModality(idPerson, newValues);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
