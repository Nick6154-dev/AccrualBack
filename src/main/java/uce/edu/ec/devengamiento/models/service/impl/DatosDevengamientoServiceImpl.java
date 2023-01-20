package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.DatosDevengamiento;
import uce.edu.ec.devengamiento.models.repository.IDatosDevengamientoRepository;
import uce.edu.ec.devengamiento.models.service.IDatosDevengamientoService;

import java.util.List;

@Service
public class DatosDevengamientoServiceImpl implements IDatosDevengamientoService {

    @Autowired
    private IDatosDevengamientoRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<DatosDevengamiento> findAll() {
        return (List<DatosDevengamiento>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public DatosDevengamiento findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(DatosDevengamiento datosDevengamiento) {
        repository.save(datosDevengamiento);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
