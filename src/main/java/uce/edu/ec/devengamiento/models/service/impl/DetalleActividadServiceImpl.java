package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.DetalleActividad;
import uce.edu.ec.devengamiento.models.repository.IDetalleActividadRepository;
import uce.edu.ec.devengamiento.models.service.IDetalleActividadService;

import java.util.List;

@Service
public class DetalleActividadServiceImpl implements IDetalleActividadService {

    @Autowired
    private IDetalleActividadRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<DetalleActividad> findAll() {
        return (List<DetalleActividad>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public DetalleActividad findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(DetalleActividad detalleActividad) {
        repository.save(detalleActividad);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
