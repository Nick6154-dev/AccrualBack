package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.TipoActividad;
import uce.edu.ec.devengamiento.models.repository.ITipoActividadRepository;
import uce.edu.ec.devengamiento.models.service.ITipoActividadService;

import java.util.List;

@Service
public class TipoActividadServiceImpl implements ITipoActividadService {

    @Autowired
    private ITipoActividadRepository repository;

    @Override
    public List<TipoActividad> findAll() {
        return (List<TipoActividad>) repository.findAll();
    }

    @Override
    public TipoActividad findById(Long id) {
        return repository.findById(id).orElse(new TipoActividad());
    }

    @Override
    public void save(TipoActividad tipoActividad) {
        repository.save(tipoActividad);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, TipoActividad tipoActividad) {
        if (repository.existsById(id)) {
            repository.save(tipoActividad);
        }
    }
}
