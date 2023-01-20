package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.Docente;
import uce.edu.ec.devengamiento.models.repository.IDocenteRepository;
import uce.edu.ec.devengamiento.models.service.IDocenteService;

import java.util.List;

@Service
public class DocenteServiceImpl implements IDocenteService {

    @Autowired
    private IDocenteRepository repository;

    @Override
    public List<Docente> findAll() {
        return (List<Docente>) repository.findAll();
    }

    @Override
    public Docente findById(Long id) {
        return repository.findById(id).orElse(new Docente());
    }

    @Override
    public void save(Docente docente) {
        repository.save(docente);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, Docente docente) {
        if (repository.existsById(id)) {
            repository.save(docente);
        }
    }
}
