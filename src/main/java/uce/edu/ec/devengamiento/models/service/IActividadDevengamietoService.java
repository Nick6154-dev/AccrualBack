package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.ActividadDevengamiento;
import uce.edu.ec.devengamiento.models.objects.OActividad;

import java.util.List;

public interface IActividadDevengamietoService {

    List<ActividadDevengamiento> findAll();

    List<ActividadDevengamiento> findAllByIdPlanDevengamiento(Long idPlan);

    ActividadDevengamiento findById(Long id);

    void save(OActividad oActividad);

    void deleteById(Long id);

    void update(Long id, ActividadDevengamiento actividadDevengamiento);

}
