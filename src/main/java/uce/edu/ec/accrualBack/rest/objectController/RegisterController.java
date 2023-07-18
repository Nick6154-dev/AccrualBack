package uce.edu.ec.accrualBack.rest.objectController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.object.RegisterObject;
import uce.edu.ec.accrualBack.service.objectService.interfaces.RegisterService;

import java.util.Map;

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
        Map<Integer, String> response = registerService.registerNewDocentByHimself(registerObject);
        if (response.containsKey(200)) return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("/ByAnotherOne")
    public ResponseEntity<?> registerByAnotherOne(@RequestBody RegisterObject registerObject) {
        Map<Integer, String> response = registerService.registerNewDocentByAnotherOne(registerObject);
        if (response.containsKey(200)) ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("/registerUserToDocentWithOutIt/{idPerson},{approved}")
    public ResponseEntity<?> registerNewUserToDocentWithOutIt(@PathVariable Long idPerson, @PathVariable boolean approved) {
        Map<Integer, String> response;
        if (approved) {
            response = registerService.registerNewUserToDocentWithOutIt(idPerson);
        } else {
            response = registerService.deleteDocentNotApproved(idPerson);
        }
        if (response.containsKey(200)) return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("/registerAllNewUsersToDocents")
    public ResponseEntity<?> registerAllNewUsersToDocents() {
        Map<Integer, String> response = registerService.registerAllNewUsersToDocents();
        if (response.containsKey(200)) return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
