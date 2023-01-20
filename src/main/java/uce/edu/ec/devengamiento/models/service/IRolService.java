package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.Rol;

import java.util.List;

public interface IRolService {

    List<Rol> findAll();

    Rol findById(Long id);

    void save(Rol rol);

    void delete(Long id);

}
