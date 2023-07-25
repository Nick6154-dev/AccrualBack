package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.entity.SocialNetwork;
import uce.edu.ec.accrualBack.service.entityService.interfaces.SocialNetworkService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.UtilCommonsService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/socialNetwork")
public class SocialNetworkController {

    @Autowired
    private SocialNetworkService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findAll());
    }

    @GetMapping("/{idSocialNetwork}")
    public ResponseEntity<?> findById(@PathVariable Long idSocialNetwork) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findById(idSocialNetwork));
    }

    @PostMapping("/save/{idPerson}")
    public ResponseEntity<?> save(@RequestBody SocialNetwork socialNetwork, @PathVariable Long idPerson) {
        Map<Integer, String> response = service.save(socialNetwork, idPerson);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/{idSocialNetwork}")
    public ResponseEntity<?> deleteById(@PathVariable Long idSocialNetwork) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteById(idSocialNetwork));
    }

    @PutMapping("/update/{idSocialNetwork}")
    public ResponseEntity<?> update(@RequestBody SocialNetwork socialNetwork, @PathVariable Long idSocialNetwork) {
        Map<Integer, String> response = service.update(socialNetwork, idSocialNetwork);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
