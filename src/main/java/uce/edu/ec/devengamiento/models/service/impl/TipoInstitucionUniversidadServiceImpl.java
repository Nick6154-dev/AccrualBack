package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.TipoInstitucionUniversidad;
import uce.edu.ec.devengamiento.models.repository.ITipoInstitucionUniversidadRepository;
import uce.edu.ec.devengamiento.models.service.ITipoInstitucionUniversidadService;

import java.util.List;

@Service
public class TipoInstitucionUniversidadServiceImpl implements ITipoInstitucionUniversidadService {

    @Autowired
    private ITipoInstitucionUniversidadRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<TipoInstitucionUniversidad> findAll() {
        return (List<TipoInstitucionUniversidad>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public TipoInstitucionUniversidad findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(TipoInstitucionUniversidad tipoInstitucionUniversidad) {
        repository.save(tipoInstitucionUniversidad);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
