package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.ActividadDocente;
import uce.edu.ec.devengamiento.models.entity.ActividadDocenteDetalle;
import uce.edu.ec.devengamiento.models.repository.IActividadDocenteDetalleRepository;
import uce.edu.ec.devengamiento.models.repository.IActividadDocenteRepository;
import uce.edu.ec.devengamiento.models.service.IActividadDocenteDetalleService;
import uce.edu.ec.devengamiento.models.service.IActividadDocenteService;

import java.util.List;

@Service
public class ActividadDocenteDetalleServiceImpl implements IActividadDocenteDetalleService {

    @Autowired
    private IActividadDocenteDetalleRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<ActividadDocenteDetalle> findAll() {
        return (List<ActividadDocenteDetalle>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public ActividadDocenteDetalle findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(ActividadDocenteDetalle actividadDocenteDetalle) {
        repository.save(actividadDocenteDetalle);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
