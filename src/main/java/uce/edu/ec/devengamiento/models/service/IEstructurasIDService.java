package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.EstructurasID;

import java.util.List;

public interface IEstructurasIDService {

    List<EstructurasID> findAll();

    EstructurasID findById(Long id);

    void save(EstructurasID estructurasID);

    void delete(Long id);

}
