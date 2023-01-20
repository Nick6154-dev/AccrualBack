package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.ActividadDocente;
import uce.edu.ec.devengamiento.models.repository.IActividadDocenteRepository;
import uce.edu.ec.devengamiento.models.service.IActividadDocenteService;

import java.util.List;

@Service
public class ActividadDocenteServiceImpl implements IActividadDocenteService {

    @Autowired
    private IActividadDocenteRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<ActividadDocente> findAll() {
        return (List<ActividadDocente>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public ActividadDocente findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(ActividadDocente actividadDocente) {
        repository.save(actividadDocente);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
