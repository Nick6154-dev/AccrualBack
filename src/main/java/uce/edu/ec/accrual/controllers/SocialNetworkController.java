package uce.edu.ec.accrual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrual.models.entity.SocialNetwork;
import uce.edu.ec.accrual.models.service.SocialNetworkService;
import uce.edu.ec.accrual.models.service.UtilCommonsService;

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
        return service.findAll();
    }

    @GetMapping("/{idSocialNetwork}")
    public ResponseEntity<?> findById(@PathVariable Long idSocialNetwork) {
        return service.findById(idSocialNetwork);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody SocialNetwork socialNetwork, BindingResult result) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return service.save(socialNetwork);
    }

    @DeleteMapping("/{idSocialNetwork}")
    public ResponseEntity<?> deleteById(@PathVariable Long idSocialNetwork) {
        return service.deleteById(idSocialNetwork);
    }

    @PutMapping("/{idSocialNetwork}")
    public ResponseEntity<?> update(@Valid @RequestBody SocialNetwork socialNetwork, BindingResult result, @PathVariable Long idSocialNetwork) {
        if (result.hasErrors()) {
            return commonsService.validate(result);
        }
        return service.update(socialNetwork, idSocialNetwork);
    }

}
