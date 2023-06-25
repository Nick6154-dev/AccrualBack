package uce.edu.ec.accrualBack.rest.objectController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrualBack.object.RegisterObject;
import uce.edu.ec.accrualBack.service.objectService.interfaces.RegisterService;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/ByHimself")
    public ResponseEntity<?> registerByHimself(@RequestBody RegisterObject registerObject) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(registerService.registerNewDocentByHimself(registerObject));
    }

    @PostMapping("/ByAnotherOne")
    public ResponseEntity<?> registerByAnotherOne(@RequestBody RegisterObject registerObject) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(registerService.registerNewDocentByAnotherOne(registerObject));
    }

}
