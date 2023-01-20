package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.Universidad;
import uce.edu.ec.devengamiento.models.repository.IUniversidadRepository;
import uce.edu.ec.devengamiento.models.service.IUniversidadService;

import java.util.List;

@Service
public class UniversidadServiceImpl implements IUniversidadService {

    @Autowired
    private IUniversidadRepository repository;

    @Override
    public List<Universidad> findAll() {
        return (List<Universidad>) repository.findAll();
    }

    @Override
    public Universidad findById(Long id) {
        return repository.findById(id).orElse(new Universidad());
    }

    @Override
    public void save(Universidad universidad) {
        repository.save(universidad);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, Universidad universidad) {
        if (repository.existsById(id)) {
            repository.save(universidad);
        }
    }
}
