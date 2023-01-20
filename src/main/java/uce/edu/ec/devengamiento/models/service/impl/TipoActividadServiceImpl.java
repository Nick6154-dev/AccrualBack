package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.TipoActividad;
import uce.edu.ec.devengamiento.models.repository.ITipoActividadRepository;
import uce.edu.ec.devengamiento.models.service.ITipoActividadService;

import java.util.List;

@Service
public class TipoActividadServiceImpl implements ITipoActividadService {

    @Autowired
    private ITipoActividadRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<TipoActividad> findAll() {
        return (List<TipoActividad>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public TipoActividad findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(TipoActividad tipoActividad) {
        repository.save(tipoActividad);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
