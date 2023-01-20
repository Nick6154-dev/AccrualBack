package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.Docente;
import uce.edu.ec.devengamiento.models.repository.IDocenteRepository;
import uce.edu.ec.devengamiento.models.service.IDocenteService;

import java.util.List;

@Service
public class DocenteServiceImpl implements IDocenteService {

    @Autowired
    private IDocenteRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Docente> findAll() {
        return (List<Docente>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Docente findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Docente docente) {
        repository.save(docente);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
