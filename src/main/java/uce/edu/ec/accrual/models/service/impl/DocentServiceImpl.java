package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.Docent;
import uce.edu.ec.accrual.models.repository.DocentRepository;
import uce.edu.ec.accrual.models.service.DocentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocentServiceImpl implements DocentService {

    @Autowired
    private DocentRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Docent> findAll() {
        return Optional.of((List<Docent>) repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Docent findById(Long idDocent) {
        return repository.findById(idDocent).orElseGet(Docent::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Docent findByIdPerson(Long idPerson) {
        return repository.findByIdPerson(idPerson).orElseGet(Docent::new);
    }

    @Override
    @Transactional
    public Docent save(Docent docent) {
        return repository.findByIdPerson(docent.getIdPerson()).orElseGet(() -> repository.save(docent));
    }

    @Override
    @Transactional
    public String deleteById(Long idDocent) {
        return repository.findById(idDocent).map(value -> "Eliminado con exito").orElseGet(() -> "No se pudo eliminar");
    }

    @Override
    @Transactional
    public Docent updateAll(Docent docent, Long idDocent) {
        return repository.findById(idDocent).map(value -> repository.save(value)).orElseGet(Docent::new);
    }
}
