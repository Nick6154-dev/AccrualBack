package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.TipoActividad;
import uce.edu.ec.devengamiento.models.entity.TipoActividadDocente;
import uce.edu.ec.devengamiento.models.repository.ITipoActividadDocenteRepository;
import uce.edu.ec.devengamiento.models.repository.ITipoActividadRepository;
import uce.edu.ec.devengamiento.models.service.ITipoActividadDocenteService;
import uce.edu.ec.devengamiento.models.service.ITipoActividadService;

import java.util.List;

@Service
public class TipoActividadDocenteServiceImpl implements ITipoActividadDocenteService {

    @Autowired
    private ITipoActividadDocenteRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<TipoActividadDocente> findAll() {
        return (List<TipoActividadDocente>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public TipoActividadDocente findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(TipoActividadDocente tipoActividadDocente) {
        repository.save(tipoActividadDocente);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
