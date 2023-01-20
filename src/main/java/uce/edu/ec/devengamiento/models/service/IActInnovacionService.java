package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.ActInnovacion;

import java.util.List;

public interface IActInnovacionService {

    List<ActInnovacion> findAll();

    ActInnovacion findById(Long id);

    void save(ActInnovacion actInnovacion);

    void deleteById(Long id);

    void update(Long id, ActInnovacion actInnovacion);

}
