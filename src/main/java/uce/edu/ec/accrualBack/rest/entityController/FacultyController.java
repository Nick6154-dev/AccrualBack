package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrualBack.entity.University;
import uce.edu.ec.accrualBack.service.entityService.interfaces.FacultyService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.UniversityService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private UniversityService universityService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(facultyService.findAll());
    }

    @GetMapping("/withoutToken")
    public ResponseEntity<?> findAllWithoutToken() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(facultyService.findAll());
    }

    @GetMapping("/{idFaculty}")
    public ResponseEntity<?> findById(@PathVariable Long idFaculty) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(facultyService.findById(idFaculty));
    }

    @GetMapping("/byIdUniversity/{idUniversity}")
    public ResponseEntity<?> findByUniversity(@PathVariable Long idUniversity) {
        University university = universityService.findById(idUniversity);
        if (university != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(facultyService.findFacultiesByUniversity(university));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro facultades para el id especificado");
    }

}
