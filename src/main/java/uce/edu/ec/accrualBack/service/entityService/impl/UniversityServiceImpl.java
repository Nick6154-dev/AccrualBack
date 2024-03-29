package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.University;
import uce.edu.ec.accrualBack.repository.UniversityRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.UniversityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<University> findAll() {
        return (List<University>) Optional.of(repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public University findById(Long idUniversity) {
        return repository.findById(idUniversity).orElseGet(University::new);
    }

    @Override
    @Transactional
    public University save(University university) {
        return repository.save(university);
    }

    @Override
    @Transactional
    public String delete(University university) {
        return repository.findById(university.getIdUniversity()).map(value -> {
            repository.delete(value);
            return "Universidad eliminada con exito";
        }).orElse("No se pudo eliminar la universidad");
    }

    @Override
    @Transactional
    public String deleteById(Long idUniversity) {
        return repository.findById(idUniversity).map(value -> {
            repository.delete(value);
            return "Universidad eliminada con exito";
        }).orElse("No se pudo eliminar la universidad");
    }

    @Override
    @Transactional
    public University update(University university, Long idUniversity) {
        return repository.findById(idUniversity).map(value -> {
            university.setIdUniversity(idUniversity);
            return repository.save(university);
        }).orElseGet(University::new);
    }
}
