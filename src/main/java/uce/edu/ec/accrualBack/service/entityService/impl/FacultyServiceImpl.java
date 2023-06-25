package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Faculty;
import uce.edu.ec.accrualBack.entity.University;
import uce.edu.ec.accrualBack.repository.FacultyRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.FacultyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Faculty> findAll() {
        return (List<Faculty>) Optional.of(repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Faculty findById(Long idFaculty) {
        return repository.findById(idFaculty).orElseGet(Faculty::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Faculty> findFacultiesByUniversity(University university) {
        return Optional.of(repository.findFacultiesByUniversity(university)).orElseGet(ArrayList::new);
    }

    @Override
    public Faculty save(Faculty faculty) {
        return repository.save(faculty);
    }

    @Override
    public String delete(Faculty faculty) {
        return repository.findById(faculty.getIdFaculty()).map(value -> {
            repository.delete(value);
            return "Facultad eliminada con exito";
        }).orElse("No se pudo eliminar la facultad");
    }

    @Override
    public String deleteById(Long idFaculty) {
        return repository.findById(idFaculty).map(value -> {
            repository.delete(value);
            return "Facultad eliminada con exito";
        }).orElse("No se pudo eliminar la facultad");
    }

    @Override
    public Faculty update(Faculty faculty, Long idFaculty) {
        return repository.findById(idFaculty).map(value -> {
            faculty.setIdFaculty(idFaculty);
            return repository.save(faculty);
        }).orElseGet(Faculty::new);
    }
}
