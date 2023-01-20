package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.OtraInstitucion;

import java.util.List;

public interface IOtraInstitucionService {

    List<OtraInstitucion> findAll();

    OtraInstitucion findById(Long id);

    void save(OtraInstitucion otraInstitucion);

    void deleteById(Long id);

    void update(Long id, OtraInstitucion otraInstitucion);

}
