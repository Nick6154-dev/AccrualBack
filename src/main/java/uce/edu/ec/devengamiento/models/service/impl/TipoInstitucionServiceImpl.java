package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.TipoInstitucion;
import uce.edu.ec.devengamiento.models.repository.ITipoInstitucionRepository;
import uce.edu.ec.devengamiento.models.service.ITipoInstitucionService;

import java.util.List;

@Service
public class TipoInstitucionServiceImpl implements ITipoInstitucionService {

    @Autowired
    private ITipoInstitucionRepository repository;

    @Override
    public List<TipoInstitucion> findAll() {
        return (List<TipoInstitucion>) repository.findAll();
    }

    @Override
    public TipoInstitucion findById(Long id) {
        return repository.findById(id).orElse(new TipoInstitucion());
    }

    @Override
    public TipoInstitucion save(TipoInstitucion tipoInstitucion) {
        return repository.save(tipoInstitucion);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, TipoInstitucion tipoInstitucion) {
        if (repository.existsById(id)) {
            repository.save(tipoInstitucion);
        }
    }
}
