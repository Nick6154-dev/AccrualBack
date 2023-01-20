package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.TipoInstitucion;
import uce.edu.ec.devengamiento.models.repository.ITipoInstitucionRepository;
import uce.edu.ec.devengamiento.models.service.ITipoInstitucionService;

import java.util.List;

@Service
public class TipoInstitucionServiceImpl implements ITipoInstitucionService {

    @Autowired
    private ITipoInstitucionRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<TipoInstitucion> findAll() {
        return (List<TipoInstitucion>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public TipoInstitucion findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(TipoInstitucion tipoInstitucion) {
        repository.save(tipoInstitucion);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
