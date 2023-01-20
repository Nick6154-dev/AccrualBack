package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.TipoActividad;
import uce.edu.ec.devengamiento.models.entity.TipoActividadInno;
import uce.edu.ec.devengamiento.models.repository.ITipoActividadInnoRepository;
import uce.edu.ec.devengamiento.models.repository.ITipoActividadRepository;
import uce.edu.ec.devengamiento.models.service.ITipoActividadInnoService;
import uce.edu.ec.devengamiento.models.service.ITipoActividadService;

import java.util.List;

@Service
public class TipoActividadInnoServiceImpl implements ITipoActividadInnoService {

    @Autowired
    private ITipoActividadInnoRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<TipoActividadInno> findAll() {
        return (List<TipoActividadInno>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public TipoActividadInno findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(TipoActividadInno tipoActividadInno) {
        repository.save(tipoActividadInno);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
