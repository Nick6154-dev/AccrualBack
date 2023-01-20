package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.Innovacion;
import uce.edu.ec.devengamiento.models.repository.IInnovacionRepository;
import uce.edu.ec.devengamiento.models.service.IInnovacionService;

import java.util.List;

@Service
public class InnovacionServiceImpl implements IInnovacionService {

    @Autowired
    private IInnovacionRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Innovacion> findAll() {
        return (List<Innovacion>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Innovacion findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Innovacion innovacion) {
        repository.save(innovacion);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
