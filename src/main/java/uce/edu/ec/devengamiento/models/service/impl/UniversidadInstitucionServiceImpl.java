package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.UniversidadInstitucion;
import uce.edu.ec.devengamiento.models.repository.IUniversidadInstitucionRepository;
import uce.edu.ec.devengamiento.models.service.IUniversidadInstitucionService;

import java.util.List;

@Service
public class UniversidadInstitucionServiceImpl implements IUniversidadInstitucionService {

    @Autowired
    private IUniversidadInstitucionRepository repository;

    @Override
    public List<UniversidadInstitucion> findAll() {
        return (List<UniversidadInstitucion>) repository.findAll();
    }

    @Override
    public UniversidadInstitucion findById(Long id) {
        return repository.findById(id).orElse(new UniversidadInstitucion());
    }

    @Override
    public void save(UniversidadInstitucion universidadInstitucion) {
        repository.save(universidadInstitucion);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, UniversidadInstitucion universidadInstitucion) {
        if (repository.existsById(id)) {
            repository.save(universidadInstitucion);
        }
    }
}
