package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.ActividadInvestigadora;
import uce.edu.ec.devengamiento.models.repository.IActividadInvestigadoraRepository;
import uce.edu.ec.devengamiento.models.service.IActividadInvestigadoraService;

import java.util.List;

@Service
public class ActividadInvestigadoraServiceImpl implements IActividadInvestigadoraService {

    @Autowired
    private IActividadInvestigadoraRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<ActividadInvestigadora> findAll() {
        return (List<ActividadInvestigadora>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public ActividadInvestigadora findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(ActividadInvestigadora actividadInvestigadora) {
        repository.save(actividadInvestigadora);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
