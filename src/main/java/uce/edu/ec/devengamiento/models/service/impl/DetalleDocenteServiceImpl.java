package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.DetalleDocente;
import uce.edu.ec.devengamiento.models.repository.IDetalleDocenteRepository;
import uce.edu.ec.devengamiento.models.service.IDetalleDocenteService;

import java.util.List;

@Service
public class DetalleDocenteServiceImpl implements IDetalleDocenteService {

    @Autowired
    private IDetalleDocenteRepository repository;

    @Override
    public List<DetalleDocente> findAll() {
        return (List<DetalleDocente>) repository.findAll();
    }

    @Override
    public DetalleDocente findById(Long id) {
        return repository.findById(id).orElse(new DetalleDocente());
    }

    @Override
    public void save(DetalleDocente detalleDocente) {
        repository.save(detalleDocente);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, DetalleDocente detalleDocente) {
        if (repository.existsById(id)) {
            repository.save(detalleDocente);
        }
    }
}
