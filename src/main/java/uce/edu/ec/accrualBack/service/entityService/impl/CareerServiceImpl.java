package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Career;
import uce.edu.ec.accrualBack.entity.Faculty;
import uce.edu.ec.accrualBack.repository.CareerRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.CareerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CareerServiceImpl implements CareerService {

    @Autowired
    private CareerRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Career> findAll() {
        return (List<Career>) Optional.of(repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Career findById(Long idCareer) {
        return repository.findById(idCareer).orElseGet(Career::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Career> findCareersByFaculty(Faculty faculty) {
        return repository.findCareersByFaculty(faculty).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional
    public Career save(Career career) {
        return repository.save(career);
    }

    @Override
    @Transactional
    public String delete(Career career) {
        return repository.findById(career.getIdCareer()).map(value -> {
            repository.delete(value);
            return "Carrera eliminada con exito";
        }).orElse("No se pudo eliminar esa carrera");
    }

    @Override
    @Transactional
    public String deleteById(Long idCareer) {
        return repository.findById(idCareer).map(value -> {
            repository.delete(value);
            return "Carrera eliminada con exito";
        }).orElse("No se pudo eliminar esa carrera");
    }

    @Override
    @Transactional
    public Career update(Career career, Long idCareer) {
        return repository.findById(idCareer).map(value -> {
            career.setIdCareer(idCareer);
            return repository.save(career);
        }).orElseGet(Career::new);
    }
}
