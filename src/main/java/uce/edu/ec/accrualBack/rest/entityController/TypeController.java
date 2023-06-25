package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrualBack.service.entityService.interfaces.TypeService;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findAll());
    }

    @GetMapping("/{idType}")
    public ResponseEntity<?> findById(@PathVariable Long idType) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findById(idType));
    }

}
