package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.ActEstructuraID;

import java.util.List;

public interface IActEstructuraIDService {

    List<ActEstructuraID> findAll();

    ActEstructuraID findById(Long id);

    void save(ActEstructuraID actEstructuraID);

    void deleteById(Long id);

    void update(Long id, ActEstructuraID actEstructuraID);

}
