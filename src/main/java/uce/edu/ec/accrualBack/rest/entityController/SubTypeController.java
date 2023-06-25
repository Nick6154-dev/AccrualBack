package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrualBack.service.entityService.interfaces.SubtypeService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.TypeService;

@RestController
@RequestMapping("/subtype")
public class SubTypeController {

    @Autowired
    private SubtypeService subtypeService;

    @Autowired
    private TypeService typeService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(subtypeService.findAll());
    }

    @GetMapping("/{idSubtype}")
    public ResponseEntity<?> findById(@PathVariable Long idSubtype) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(subtypeService.findById(idSubtype));
    }

    @GetMapping("/byIdType/{idType}")
    public ResponseEntity<?> findSubtypeByType(@PathVariable Long idType) {
        if (typeService.findById(idType) != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(subtypeService.findSubtypesByType(typeService.findById(idType)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id proporcionado no se ha encontrado en el sistema");
    }

}
