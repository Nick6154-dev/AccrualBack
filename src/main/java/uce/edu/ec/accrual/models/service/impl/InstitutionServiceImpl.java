package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Institution;
import uce.edu.ec.accrual.models.repository.InstitutionRepository;
import uce.edu.ec.accrual.models.service.InstitutionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Institution> findAll() {
        return (List<Institution>) Optional.of(repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Institution findById(Long idInstitution) {
        return repository.findById(idInstitution).orElseGet(Institution::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Institution findInstitutionByActivity(Long idActivity) {
        return repository.findInstitutionByIdActivity(idActivity).orElseGet(Institution::new);
    }

    @Override
    @Transactional
    public Institution save(Institution institution) {
        return repository.save(institution);
    }

    @Override
    @Transactional
    public String deleteById(Long idInstitution) {
        return repository.findById(idInstitution).map(value -> {
            repository.deleteById(idInstitution);
            return "Eliminado con exito";
        }).orElseGet(() -> "No se ha encontrado el id especificado");
    }

    @Override
    @Transactional
    public Institution update(Institution institution, Long idInstitution) {
        return repository.findById(idInstitution).map(value -> {
            institution.setIdInstitution(idInstitution);
            return repository.save(institution);
        }).orElseGet(Institution::new);
    }
}
