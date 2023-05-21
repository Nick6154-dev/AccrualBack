package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Faculty;
import uce.edu.ec.accrual.models.entity.University;
import uce.edu.ec.accrual.models.repository.FacultyRepository;
import uce.edu.ec.accrual.models.service.FacultyService;

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
}
