package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.Universidad;

import java.util.List;

public interface IUniversidadService {

    List<Universidad> findAll();

    Universidad findById(Long id);

    void save(Universidad universidad);

    void deleteById(Long id);

    void update(Long id, Universidad universidad);

}
