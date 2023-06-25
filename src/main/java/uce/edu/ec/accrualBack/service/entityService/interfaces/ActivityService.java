package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Activity;

import java.util.List;

public interface ActivityService {

    List<Activity> findAll();

    Activity findById(Long idActivity);

    Activity save(Activity activity);

    String delete(Activity activity);

    String deleteById(Long idActivity);

    Activity update(Activity activity, Long idActivity);

}
