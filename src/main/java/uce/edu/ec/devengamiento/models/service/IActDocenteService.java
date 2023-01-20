package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.ActDocente;

import java.util.List;

public interface IActDocenteService {

    List<ActDocente> findAll();

    ActDocente findById(Long id);

    void save(ActDocente actDocente);

    void deleteById(Long id);

    void update(Long id, ActDocente actDocente);

}
