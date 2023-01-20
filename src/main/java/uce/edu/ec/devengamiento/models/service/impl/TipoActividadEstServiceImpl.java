package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.TipoActividad;
import uce.edu.ec.devengamiento.models.entity.TipoActividadEst;
import uce.edu.ec.devengamiento.models.repository.ITipoActividadEstRepository;
import uce.edu.ec.devengamiento.models.repository.ITipoActividadRepository;
import uce.edu.ec.devengamiento.models.service.ITipoActividadEstService;
import uce.edu.ec.devengamiento.models.service.ITipoActividadService;

import java.util.List;

@Service
public class TipoActividadEstServiceImpl implements ITipoActividadEstService {

    @Autowired
    private ITipoActividadEstRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<TipoActividadEst> findAll() {
        return (List<TipoActividadEst>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public TipoActividadEst findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(TipoActividadEst tipoActividadEst) {
        repository.save(tipoActividadEst);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
