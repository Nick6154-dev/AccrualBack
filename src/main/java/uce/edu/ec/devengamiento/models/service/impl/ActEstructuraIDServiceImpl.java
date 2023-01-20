package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.ActEstructuraID;
import uce.edu.ec.devengamiento.models.repository.IActEstructuraIDRepository;
import uce.edu.ec.devengamiento.models.service.IActEstructuraIDService;

import java.util.List;

@Service
public class ActEstructuraIDServiceImpl implements IActEstructuraIDService {

    @Autowired
    private IActEstructuraIDRepository repository;

    @Override
    public List<ActEstructuraID> findAll() {
        return (List<ActEstructuraID>) repository.findAll();
    }

    @Override
    public ActEstructuraID findById(Long id) {
        return repository.findById(id).orElse(new ActEstructuraID());
    }

    @Override
    public void save(ActEstructuraID actEstructuraID) {
        repository.save(actEstructuraID);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, ActEstructuraID actEstructuraID) {
        if (repository.existsById(id)) {
            repository.save(actEstructuraID);
        }
    }
}
