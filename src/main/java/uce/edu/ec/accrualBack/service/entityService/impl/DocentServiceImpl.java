package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.AccrualData;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.repository.DocentRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.AccrualDataService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DocentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocentServiceImpl implements DocentService {

    @Autowired
    private DocentRepository repository;

    @Autowired
    private AccrualDataService accrualDataService;

    @Override
    @Transactional(readOnly = true)
    public List<Docent> findAll() {
        return Optional.of((List<Docent>) repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Docent> findAllDocentSettlementNoApproved() {
        return accrualDataService.findAllByAccrualData(false)
                .stream()
                .map(AccrualData::getDocent)
                .collect(Collectors.toList());
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
