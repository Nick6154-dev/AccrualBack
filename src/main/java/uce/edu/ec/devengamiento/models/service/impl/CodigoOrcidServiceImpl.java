package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.CodigoOrcid;
import uce.edu.ec.devengamiento.models.repository.ICodigoOrcidRepository;
import uce.edu.ec.devengamiento.models.service.ICodigoOrcidService;

import java.util.List;

@Service
public class CodigoOrcidServiceImpl implements ICodigoOrcidService {

    @Autowired
    private ICodigoOrcidRepository repository;

    @Override
    public List<CodigoOrcid> findAll() {
        return (List<CodigoOrcid>) repository.findAll();
    }

    @Override
    public CodigoOrcid findById(Long id) {
        return repository.findById(id).orElse(new CodigoOrcid());
    }

    @Override
    public void save(CodigoOrcid codigoOrcid) {
        repository.save(codigoOrcid);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, CodigoOrcid codigoOrcid) {
        if (repository.existsById(id)) {
            repository.save(codigoOrcid);
        }
    }
}
