package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.DatosDevengamiento;
import uce.edu.ec.devengamiento.models.entity.Docente;
import uce.edu.ec.devengamiento.models.repository.IDatosDevengamientoRepository;
import uce.edu.ec.devengamiento.models.repository.IDocenteRepository;
import uce.edu.ec.devengamiento.models.service.IDatosDevengamientoService;

import java.util.List;

@Service
public class DatosDevengamientoServiceImpl implements IDatosDevengamientoService {

    @Autowired
    private IDatosDevengamientoRepository repository;

    @Autowired
    private IDocenteRepository docenteRepository;

    @Override
    public List<DatosDevengamiento> findAll() {
        return (List<DatosDevengamiento>) repository.findAll();
    }

    @Override
    public DatosDevengamiento findById(Long id) {
        return repository.findById(id).orElse(new DatosDevengamiento());
    }

    @Override
    public DatosDevengamiento findByIdDocente(Long idDocente) {
        return repository.findDatosDevengamientoByIdDocente(docenteRepository.findById(idDocente).orElse(new Docente()));
    }

    @Override
    public void save(DatosDevengamiento datosDevengamiento, Long idDocente) {
        datosDevengamiento.setIdDocente(docenteRepository.findById(idDocente).orElse(new Docente()));
        repository.save(datosDevengamiento);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, DatosDevengamiento datosDevengamiento) {
        if (repository.existsById(id)) {
            repository.save(datosDevengamiento);
        }
    }
}
