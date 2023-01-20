package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.Docente;

import java.util.List;

public interface IDocenteService {

    List<Docente> findAll();

    Docente findById(Long id);

    void save(Docente docente);

    void deleteById(Long id);

    void update(Long id, Docente docente);

}
