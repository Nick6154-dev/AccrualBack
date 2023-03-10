package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.entity.Plan;
import uce.edu.ec.accrual.models.repository.DocentRepository;
import uce.edu.ec.accrual.models.repository.PlanRepository;
import uce.edu.ec.accrual.models.service.PlanService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository repository;

    @Autowired
    private DocentRepository docentRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        return Optional.of((List<Plan>) repository.findAll()).map(value ->
                ResponseEntity.status(HttpStatus.ACCEPTED).body(value)
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ArrayList<>()));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long idPlan) {
        return repository.findById(idPlan).map(value ->
                ResponseEntity.status(HttpStatus.ACCEPTED).body(value)
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Plan()));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findByIdPersonAndPeriod(Long idPerson, String period) {
        Optional<Docent> docent = docentRepository.findByIdPerson(idPerson);
        if (docent.isPresent()) {
            return repository.findByPeriodAndIdDocent(period, docent.get().getIdDocent())
                    .map(value -> ResponseEntity.status(HttpStatus.ACCEPTED).body(value.getIdPlan()))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se ha encontrado un plan asignado a ese id persona");
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(Plan plan) {
        return repository.findByPeriodAndIdDocent(plan.getPeriod(), plan.getIdDocent())
                .map(value -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(value))
                .orElseGet(() -> {
                    Optional<Plan> optionalPlan1 = repository.findNextNumberPlanByIdDocent(plan.getIdDocent());
                    return optionalPlan1.map(value2 -> {
                        plan.setNumberPlan(value2.getNumberPlan() + 1);
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(plan));
                    }).orElseGet(() -> {
                        plan.setNumberPlan(1);
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(plan));
                    });
                });
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(Long idPlan) {
        return repository.findById(idPlan).map(value -> {
            repository.deleteById(idPlan);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado con exito");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se ha podido eliminar"));
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Plan plan, Long idPlan) {
        return repository.findById(idPlan).map(value ->
                ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(plan))
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Plan()));
    }

    @Override
    public ResponseEntity<?> updateNotEditable(Long idPerson, String period) {
        Optional<Docent> docent = docentRepository.findByIdPerson(idPerson);
        return docent.map(docent1 -> repository.findByPeriodAndIdDocent(period, docent1.getIdDocent())
                        .map(value -> {
                            value.setEditable(false);
                            repository.save(value);
                            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Este plan ya no se podra editar");
                        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se pudo encontrar un plan")))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se ha encontrado un plan asignado a ese id persona"));
    }
}
