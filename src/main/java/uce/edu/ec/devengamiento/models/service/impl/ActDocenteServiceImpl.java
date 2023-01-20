package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.ActDocente;
import uce.edu.ec.devengamiento.models.repository.IActDocenteRepository;
import uce.edu.ec.devengamiento.models.service.IActDocenteService;

import java.util.List;

@Service
public class ActDocenteServiceImpl implements IActDocenteService {

    @Autowired
    private IActDocenteRepository repository;

    @Override
    public List<ActDocente> findAll() {
        return (List<ActDocente>) repository.findAll();
    }

    @Override
    public ActDocente findById(Long id) {
        return repository.findById(id).orElse(new ActDocente());
    }

    @Override
    public void save(ActDocente actDocente) {
        repository.save(actDocente);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, ActDocente actDocente) {
        if (repository.existsById(id)) {
            repository.save(actDocente);
        }
    }
}
