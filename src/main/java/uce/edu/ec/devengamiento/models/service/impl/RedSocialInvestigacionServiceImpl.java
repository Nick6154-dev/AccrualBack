package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.RedSocialInvestigacion;
import uce.edu.ec.devengamiento.models.repository.IRedSocialInvestigacionRepository;
import uce.edu.ec.devengamiento.models.service.IRedSocialInvestigacionService;

import java.util.List;

@Service
public class RedSocialInvestigacionServiceImpl implements IRedSocialInvestigacionService {

    @Autowired
    private IRedSocialInvestigacionRepository repository;

    @Override
    public List<RedSocialInvestigacion> findAll() {
        return (List<RedSocialInvestigacion>) repository.findAll();
    }

    @Override
    public RedSocialInvestigacion findById(Long id) {
        return repository.findById(id).orElse(new RedSocialInvestigacion());
    }

    @Override
    public void save(RedSocialInvestigacion redSocialInvestigacion) {
        repository.save(redSocialInvestigacion);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, RedSocialInvestigacion redSocialInvestigacion) {
        if (repository.existsById(id)) {
            repository.save(redSocialInvestigacion);
        }
    }
}
