package uce.edu.ec.devengamiento.models.service;


import uce.edu.ec.devengamiento.models.entity.DetalleDocente;

import java.util.List;

public interface IDetalleDocenteService {

    List<DetalleDocente> findAll();

    DetalleDocente findById(Long id);

    void save(DetalleDocente detalleDocente);

    void deleteById(Long id);

    void update(Long id, DetalleDocente detalleDocente);

}
