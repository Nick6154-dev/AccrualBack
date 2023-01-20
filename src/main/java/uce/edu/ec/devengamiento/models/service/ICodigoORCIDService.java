package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.CodigoOrcid;

import java.util.List;

public interface ICodigoORCIDService {

    List<CodigoOrcid> findAll();

    CodigoOrcid findById(Long id);

    void save(CodigoOrcid codigoOrcid);

    void delete(Long id);

}
