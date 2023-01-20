package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.CodigoOrcid;

import java.util.List;

public interface ICodigoOrcidService {

    List<CodigoOrcid> findAll();

    CodigoOrcid findById(Long id);

    void save(CodigoOrcid codigoOrcid);

    void deleteById(Long id);

    void update(Long id, CodigoOrcid codigoOrcid);


}
