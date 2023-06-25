package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrualBack.entity.Faculty;
import uce.edu.ec.accrualBack.service.entityService.interfaces.CareerService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.FacultyService;

@RestController
@RequestMapping("/career")
public class CareerController {

    @Autowired
    private CareerService careerService;

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(careerService.findAll());
    }

    @GetMapping("/{idCareer}")
    public ResponseEntity<?> findById(@PathVariable Long idCareer) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(careerService.findById(idCareer));
    }

    @GetMapping("/byIdFaculty/{idFaculty}")
    public ResponseEntity<?> findByFaculty(@PathVariable Long idFaculty) {
        Faculty faculty = facultyService.findById(idFaculty);
        if (faculty != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(careerService.findCareersByFaculty(faculty));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado carreras para el id especificado");
    }

}
