package uce.edu.ec.accrualBack.service.objectService.interfaces;

import uce.edu.ec.accrualBack.object.Converter;

public interface PlanInstitutionActivityService {

    String addNewActivityWithInstitution(Converter converter);

    String deleteActivityWithInstitution(Long idActivityPlan);

    String updateActivityWithInstitution(Converter converter, Long idActivityPlan);

}
