package uce.edu.ec.accrual.models.service;

import uce.edu.ec.accrual.models.entity.ActivityPlan;
import uce.edu.ec.accrual.models.entity.Description;

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
