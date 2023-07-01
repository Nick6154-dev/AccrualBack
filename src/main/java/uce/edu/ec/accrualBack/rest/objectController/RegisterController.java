package uce.edu.ec.accrualBack.rest.objectController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.object.RegisterObject;
import uce.edu.ec.accrualBack.service.objectService.interfaces.RegisterService;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/listDocentsWithOutUser")
    public ResponseEntity<?> findAllDocentsWithOutUser() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(registerService.listDocentsWithOutUser());
    }

    @PostMapping("/ByHimself")
    public ResponseEntity<?> registerByHimself(@RequestBody RegisterObject registerObject) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(registerService.registerNewDocentByHimself(registerObject));
    }

    @PostMapping("/ByAnotherOne")
    public ResponseEntity<?> registerByAnotherOne(@RequestBody RegisterObject registerObject) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(registerService.registerNewDocentByAnotherOne(registerObject));
    }

    @PostMapping("/registerUserToDocentWithOutIt/{idPerson},{approved}")
    public ResponseEntity<?> registerNewUserToDocentWithOutIt(@PathVariable Long idPerson, @PathVariable boolean approved) {
        if (approved) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(registerService.registerNewUserToDocentWithOutIt(idPerson));
        }
        registerService.deleteDocentNotApproved(idPerson);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Docente eliminado con exito");
    }

}
