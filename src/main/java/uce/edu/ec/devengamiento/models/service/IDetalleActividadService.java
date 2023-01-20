package uce.edu.ec.devengamiento.models.service;

import uce.edu.ec.devengamiento.models.entity.DetalleActividad;

import java.util.List;

public interface IDetalleActividadService {

    List<DetalleActividad> findAll();

    DetalleActividad findById(Long id);

    void save(DetalleActividad detalleActividad);

    void delete(Long id);

}
