package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.ActInvestigadora;
import uce.edu.ec.devengamiento.models.repository.IActInvestigadoraRepository;
import uce.edu.ec.devengamiento.models.service.IActInvestigadoraService;

import java.util.List;

@Service
public class ActInvestigadoraServiceImpl implements IActInvestigadoraService {

    @Autowired
    private IActInvestigadoraRepository repository;

    @Override
    public List<ActInvestigadora> findAll() {
        return (List<ActInvestigadora>) repository.findAll();
    }

    @Override
    public ActInvestigadora findById(Long id) {
        return repository.findById(id).orElse(new ActInvestigadora());
    }

    @Override
    public void save(ActInvestigadora actInvestigadora) {
        repository.save(actInvestigadora);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, ActInvestigadora actInvestigadora) {
        if (repository.existsById(id)) {
            repository.save(actInvestigadora);
        }
    }
}
