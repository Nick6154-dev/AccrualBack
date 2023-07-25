package uce.edu.ec.accrualBack.rest.entityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Network;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DocentService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.NetworkService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.UtilCommonsService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/network")
public class NetworkController {

    @Autowired
    private NetworkService service;

    @Autowired
    private UtilCommonsService commonsService;

    @Autowired
    private DocentService docentService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findAll());
    }

    @GetMapping("/{idNetwork}")
    public ResponseEntity<?> findById(@PathVariable Long idNetwork) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findById(idNetwork));
    }

    @GetMapping("/byIdDocent/{idDocent}")
    public ResponseEntity<?> findByDocent(@PathVariable Long idDocent) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findByDocent(docentService.findById(idDocent)));
    }

    @GetMapping("/byIdPerson/{idPerson}")
    public ResponseEntity<?> findByIdPerson(@PathVariable Long idPerson) {
        Docent docent = docentService.findByIdPerson(idPerson);
        if (docent.getCategory() != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findByDocent(docent));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Id persona no encontrada : " + idPerson);
        }
    }

    @PostMapping("/save/{idPerson}")
    public ResponseEntity<?> save(@RequestBody Network network, @PathVariable Long idPerson) {
        Map<Integer, String> response = service.save(network, idPerson);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/{idNetwork}")
    public ResponseEntity<?> deleteById(@PathVariable Long idNetwork) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteById(idNetwork));
    }

    @PutMapping("/update/{idNetwork}")
    public ResponseEntity<?> update(@RequestBody Network network, @PathVariable Long idNetwork) {
        Map<Integer, String> response = service.update(network, idNetwork);
        if (response.containsKey(400)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
