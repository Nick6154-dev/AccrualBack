package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Activity;
import uce.edu.ec.accrualBack.repository.ActivityRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.ActivityService;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Activity> findAll() {
        return (List<Activity>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Activity findById(Long idActivity) {
        return repository.findById(idActivity).orElseGet(Activity::new);
    }

    @Override
    @Transactional
    public Activity save(Activity activity) {
        return repository.save(activity);
    }

    @Override
    @Transactional
    public String delete(Activity activity) {
        return repository.findById(activity.getIdActivity()).map(value -> {
            repository.delete(value);
            return "Eliminado con exito";
        }).orElse("Error: No se encontro esa actividad");
    }

    @Override
    @Transactional
    public String deleteById(Long idActivity) {
        return repository.findById(idActivity).map(value -> {
            repository.deleteById(idActivity);
            return "Eliminado con exito";
        }).orElse("Error: No se encontro esa actividad");
    }

    @Override
    @Transactional
    public Activity update(Activity activity, Long idActivity) {
        return repository.findById(idActivity).map(value -> {
            activity.setIdActivity(value.getIdActivity());
            return repository.save(activity);
        }).orElseGet(Activity::new);
    }
}
