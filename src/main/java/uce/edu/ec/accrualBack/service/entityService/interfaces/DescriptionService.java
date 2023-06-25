package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.ActivityPlan;
import uce.edu.ec.accrualBack.entity.Description;

import java.util.List;

public interface DescriptionService {

    List<Description> findAll();

    Description findById(Long idDescription);

    Description findDescriptionByActivityPlan(ActivityPlan activityPlan);

    Description save(Description description);

    String delete(Description description);

    String deleteById(Long idDescription);

    Description update(Description description, Long idDescription);

}
