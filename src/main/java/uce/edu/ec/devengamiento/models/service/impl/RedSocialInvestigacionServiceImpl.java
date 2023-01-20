package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.RedSocialInvestigacion;
import uce.edu.ec.devengamiento.models.repository.IRedSocialInvestigacionRepository;
import uce.edu.ec.devengamiento.models.service.IRedSocialInvestigacionService;

import java.util.List;

@Service
public class RedSocialInvestigacionServiceImpl implements IRedSocialInvestigacionService {

    @Autowired
    private IRedSocialInvestigacionRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<RedSocialInvestigacion> findAll() {
        return (List<RedSocialInvestigacion>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public RedSocialInvestigacion findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(RedSocialInvestigacion redSocialInvestigacion) {
        repository.save(redSocialInvestigacion);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
