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

@RestController
@RequestMapping("/socialNetwork")
public class SocialNetworkController {

    @Autowired
    private SocialNetworkService service;

    @Autowired
    private UtilCommonsService commonsService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findAll());
    }

    @GetMapping("/{idSocialNetwork}")
    public ResponseEntity<?> findById(@PathVariable Long idSocialNetwork) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findById(idSocialNetwork));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody SocialNetwork socialNetwork, BindingResult result) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.save(socialNetwork));
    }

    @DeleteMapping("/{idSocialNetwork}")
    public ResponseEntity<?> deleteById(@PathVariable Long idSocialNetwork) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteById(idSocialNetwork));
    }

    @PutMapping("/{idSocialNetwork}")
    public ResponseEntity<?> update(@Valid @RequestBody SocialNetwork socialNetwork, BindingResult result, @PathVariable Long idSocialNetwork) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(socialNetwork, idSocialNetwork));
    }

}
