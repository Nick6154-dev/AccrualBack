package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.Innovacion;

import java.util.List;

public interface IInnovacionService {

    List<Innovacion> findAll();

    Innovacion findById(Long id);

    void save(Innovacion innovacion);

    void delete(Long id);

}
