package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.RedSocialInvestigacion;

import java.util.List;

public interface IRedSocialInvestigacionService {

    List<RedSocialInvestigacion> findAll();

    RedSocialInvestigacion findById(Long id);

    void save(RedSocialInvestigacion redSocialInvestigacion);

    void delete(Long id);

}
