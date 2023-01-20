package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.ActInnovacion;
import uce.edu.ec.devengamiento.models.repository.IActInnovacionRepository;
import uce.edu.ec.devengamiento.models.service.IActInnovacionService;

import java.util.List;

@Service
public class ActInnovacionServiceImpl implements IActInnovacionService {

    @Autowired
    private IActInnovacionRepository repository;

    @Override
    public List<ActInnovacion> findAll() {
        return (List<ActInnovacion>) repository.findAll();
    }

    @Override
    public ActInnovacion findById(Long id) {
        return repository.findById(id).orElse(new ActInnovacion());
    }

    @Override
    public void save(ActInnovacion actInnovacion) {
        repository.save(actInnovacion);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, ActInnovacion actInnovacion) {
        if (repository.existsById(id)) {
            repository.save(actInnovacion);
        }
    }
}
