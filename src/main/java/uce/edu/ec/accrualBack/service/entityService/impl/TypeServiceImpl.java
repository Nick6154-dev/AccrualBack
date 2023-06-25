package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Type;
import uce.edu.ec.accrualBack.repository.TypeRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.TypeService;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Type> findAll() {
        return (List<Type>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Type findById(Long idType) {
        return repository.findById(idType).orElseGet(Type::new);
    }

    @Override
    @Transactional
    public Type save(Type type) {
        return repository.save(type);
    }

    @Override
    @Transactional
    public String delete(Type type) {
        return repository.findById(type.getIdActivityType()).map(value -> {
            repository.delete(value);
            return "Tipo eliminado con exito";
        }).orElse("No se pudo eliminar el tipo");
    }

    @Override
    @Transactional
    public String deleteById(Long idType) {
        return repository.findById(idType).map(value -> {
            repository.delete(value);
            return "Tipo elimnado con exito";
        }).orElse("No se pudo elimnar el tipo");
    }

    @Override
    @Transactional
    public Type update(Type type, Long idType) {
        return repository.findById(idType).map(value -> {
            type.setIdActivityType(idType);
            return repository.save(type);
        }).orElseGet(Type::new);
    }
}
