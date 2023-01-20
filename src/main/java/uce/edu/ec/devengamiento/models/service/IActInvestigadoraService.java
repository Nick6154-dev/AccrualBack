package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.ActInvestigadora;

import java.util.List;

public interface IActInvestigadoraService {

    List<ActInvestigadora> findAll();

    ActInvestigadora findById(Long id);

    void save(ActInvestigadora actInvestigadora);

    void deleteById(Long id);

    void update(Long id, ActInvestigadora actInvestigadora);

}
