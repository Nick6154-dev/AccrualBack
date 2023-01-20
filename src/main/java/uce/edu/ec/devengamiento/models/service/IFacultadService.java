package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.Facultad;
import uce.edu.ec.devengamiento.models.entity.Universidad;

import java.util.List;

public interface IFacultadService {

    List<Facultad> findAll();

    List<Facultad> findByIdUniversidad(Long idUniversidad);

    Facultad findById(Long id);

    void save(Facultad facultad);

    void delete(Long id);

}
