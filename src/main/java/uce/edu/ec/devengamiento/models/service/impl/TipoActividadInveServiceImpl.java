package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.TipoActividad;
import uce.edu.ec.devengamiento.models.entity.TipoActividadInve;
import uce.edu.ec.devengamiento.models.repository.ITipoActividadInveRepository;
import uce.edu.ec.devengamiento.models.repository.ITipoActividadRepository;
import uce.edu.ec.devengamiento.models.service.ITipoActividadInveService;
import uce.edu.ec.devengamiento.models.service.ITipoActividadService;

import java.util.List;

@Service
public class TipoActividadInveServiceImpl implements ITipoActividadInveService {

    @Autowired
    private ITipoActividadInveRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<TipoActividadInve> findAll() {
        return (List<TipoActividadInve>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public TipoActividadInve findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(TipoActividadInve tipoActividadInve) {
        repository.save(tipoActividadInve);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
