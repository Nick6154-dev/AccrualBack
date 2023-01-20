package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.OtraInstitucion;
import uce.edu.ec.devengamiento.models.repository.IOtraInstitucionRepository;
import uce.edu.ec.devengamiento.models.service.IOtraInstitucionService;

import java.util.List;

@Service
public class OtraInstitucionServiceImpl implements IOtraInstitucionService {

    @Autowired
    private IOtraInstitucionRepository repository;

    @Override
    public List<OtraInstitucion> findAll() {
        return (List<OtraInstitucion>) repository.findAll();
    }

    @Override
    public OtraInstitucion findById(Long id) {
        return repository.findById(id).orElse(new OtraInstitucion());
    }

    @Override
    public void save(OtraInstitucion otraInstitucion) {
        repository.save(otraInstitucion);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, OtraInstitucion otraInstitucion) {
        if (repository.existsById(id)) {
            repository.save(otraInstitucion);
        }
    }
}
