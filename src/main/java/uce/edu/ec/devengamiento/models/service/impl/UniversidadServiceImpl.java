package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.Universidad;
import uce.edu.ec.devengamiento.models.repository.IUniversidadRepository;
import uce.edu.ec.devengamiento.models.service.IUniversidadService;

import java.util.List;

@Service
public class UniversidadServiceImpl implements IUniversidadService {

    @Autowired
    private IUniversidadRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Universidad> findAll() {
        return (List<Universidad>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Universidad findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Universidad universidad) {
        repository.save(universidad);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
