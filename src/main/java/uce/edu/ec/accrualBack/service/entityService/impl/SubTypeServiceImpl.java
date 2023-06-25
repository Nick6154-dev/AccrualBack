package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Subtype;
import uce.edu.ec.accrualBack.entity.Type;
import uce.edu.ec.accrualBack.repository.SubtypeRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.SubtypeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubTypeServiceImpl implements SubtypeService {

    @Autowired
    private SubtypeRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Subtype> findAll() {
        return (List<Subtype>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Subtype findById(Long idSubtype) {
        return repository.findById(idSubtype).orElseGet(Subtype::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subtype> findSubtypesByType(Type type) {
        return repository.findSubtypesByActivityType(type).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional
    public Subtype save(Subtype subtype) {
        return repository.save(subtype);
    }

    @Override
    @Transactional
    public String delete(Subtype subtype) {
        return repository.findById(subtype.getIdActivitySubtype()).map(value -> {
            repository.delete(value);
            return "Subtipo eliminado con exito";
        }).orElse("No se pudo eliminar el subtipo");
    }

    @Override
    @Transactional
    public String deleteById(Long idSubtype) {
        return repository.findById(idSubtype).map(value -> {
            repository.delete(value);
            return "Subtipo eliminado con exito";
        }).orElse("No se pudo eliminar el subtipo");
    }

    @Override
    @Transactional
    public Subtype update(Subtype subtype, Long idSubtype) {
        return repository.findById(idSubtype).map(value -> {
            subtype.setIdActivitySubtype(idSubtype);
            return repository.save(subtype);
        }).orElseGet(Subtype::new);
    }
}
