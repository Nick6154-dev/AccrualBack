package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.ActivityPlan;
import uce.edu.ec.accrualBack.entity.Description;
import uce.edu.ec.accrualBack.repository.DescriptionRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DescriptionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DescriptionServiceImpl implements DescriptionService {

    @Autowired
    private DescriptionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Description> findAll() {
        return (List<Description>) Optional.of(repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Description findById(Long idDescription) {
        return repository.findById(idDescription).orElseGet(Description::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Description findDescriptionByActivityPlan(ActivityPlan activityPlan) {
        return repository.findDescriptionByActivityPlan(activityPlan).orElseGet(Description::new);
    }

    @Override
    public Description save(Description description) {
        return repository.save(description);
    }

    @Override
    public String delete(Description description) {
        return repository.findById(description.getIdDescriptionSubtype()).map(value -> {
            repository.delete(value);
            return "Eliminado la descripcion subtipo con exito";
        }).orElseGet(() -> "Problemas al eliminar la descripcion subtipo");
    }

    @Override
    public String deleteById(Long idDescription) {
        return repository.findById(idDescription).map(value -> {
            repository.delete(value);
            return "Eliminado la descripcion subtipo con exito";
        }).orElseGet(() -> "Problemas al eliminar la descripcion subtipo");
    }

    @Override
    public Description update(Description description, Long idDescription) {
        return repository.findById(idDescription).map(value -> {
            description.setIdDescriptionSubtype(idDescription);
            return repository.save(description);
        }).orElseGet(Description::new);
    }
}
