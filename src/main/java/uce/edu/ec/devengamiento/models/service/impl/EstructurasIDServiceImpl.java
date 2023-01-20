package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.EstructurasID;
import uce.edu.ec.devengamiento.models.repository.IEstructurasIDRepository;
import uce.edu.ec.devengamiento.models.service.IEstructurasIDService;

import java.util.List;

@Service
public class EstructurasIDServiceImpl implements IEstructurasIDService {

    @Autowired
    private IEstructurasIDRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<EstructurasID> findAll() {
        return (List<EstructurasID>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public EstructurasID findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(EstructurasID estructurasID) {
        repository.save(estructurasID);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
