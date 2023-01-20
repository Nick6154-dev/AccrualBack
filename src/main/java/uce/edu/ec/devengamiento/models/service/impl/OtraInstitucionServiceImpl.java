package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.OtraInstitucion;
import uce.edu.ec.devengamiento.models.repository.IOtraInstitucionRepository;
import uce.edu.ec.devengamiento.models.service.IOtraInstitucionService;

import java.util.List;

@Service
public class OtraInstitucionServiceImpl implements IOtraInstitucionService {

    @Autowired
    private IOtraInstitucionRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<OtraInstitucion> findAll() {
        return (List<OtraInstitucion>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public OtraInstitucion findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(OtraInstitucion otraInstitucion) {
        repository.save(otraInstitucion);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
