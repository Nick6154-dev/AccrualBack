package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.CodigoOrcid;
import uce.edu.ec.devengamiento.models.repository.ICodigoORCIDRepository;
import uce.edu.ec.devengamiento.models.service.ICodigoORCIDService;

import java.util.List;

@Service
public class CodigoORCIDServiceImpl implements ICodigoORCIDService {

    @Autowired
    private ICodigoORCIDRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<CodigoOrcid> findAll() {
        return (List<CodigoOrcid>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public CodigoOrcid findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(CodigoOrcid codigoOrcid) {
        repository.save(codigoOrcid);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
