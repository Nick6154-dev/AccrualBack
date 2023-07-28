package uce.edu.ec.accrualBack.service.objectService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.*;
import uce.edu.ec.accrualBack.object.PlanInstitutionActivity;
import uce.edu.ec.accrualBack.service.entityService.interfaces.*;
import uce.edu.ec.accrualBack.service.objectService.interfaces.PlanInstitutionActivityService;

import java.util.*;

@Service
public class PlanInstitutionActivityServiceImpl implements PlanInstitutionActivityService {

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private ActivityPlanService activityPlanService;

    @Autowired
    private DocentService docentService;

    @Autowired
    private PlanService planService;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private DescriptionService descriptionService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private SubtypeService subtypeService;

    @Autowired
    private CareerService careerService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private OtherInstitutionService otherInstitutionService;

    @Autowired
    private UniversityInstitutionService universityInstitutionService;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private PeriodDocentService periodDocentService;

    /*
        Methods to register new activities
    */
    @Override
    @Transactional
    public Map<Integer, String> addNewActivityWithInstitution(PlanInstitutionActivity activityPlanInstitution) {
        Map<Integer, String> response = new HashMap<>();
        Docent docent = docentService.findByIdPerson(activityPlanInstitution.getIdPerson());
        if (docent.getIdDocent() == null) {
            response.put(400, "El id no pertenece a ningun docente");
            return response;
        }
        Period period = periodService.findById(activityPlanInstitution.getIdPeriod());
        Plan plan = planService.findByIdPersonAndPeriod(activityPlanInstitution.getIdPerson(), period);
        if (plan.getIdPlan() == null) {
            plan.setPeriod(period);
            plan.setIdDocent(docent.getIdDocent());
            plan = planService.save(plan);
        }
        if (!plan.getEditable()) {
            response.put(400, "Ya no se agregan mas actividades pues ya no es editable");
            return response;
        }
        if (!plan.getPeriod().getActive()) {
            response.put(400, "Ya no se pueden agregar mas actividades, el periodo ya no esta activo");
            return response;
        }
        if (plan.getPeriod().getState() == 3) {
            response.put(400, "Ya no se pueden agregar mas actividades, la etapa de registro ya paso");
            return response;
        }
        if (plan.getPeriod().getState() == 0) {
            response.put(400, "No se pueden agregar actividades pues el periodo aun no tiene un modo");
            return response;
        }
        if (!periodDocentService.existsByIdDocentAndIdPeriod(plan.getIdDocent(), plan.getPeriod().getIdPeriod())) {
            response.put(400, "El docente o el periodo no pertenecen para guardar una nueva actividad");
            return response;
        }
        Map<Integer, String> validator = validateObjectPlanInstitutionActivity(activityPlanInstitution);
        if (validator.containsKey(400)) {
            response.putAll(validator);
            return response;
        }
        Map<Integer, Object> resultActivityPlan = saveActivityPlan(activityPlanInstitution, plan);
        if (resultActivityPlan.containsKey(400)) {
            response.put(400, "Error dentro de guardad actividad plan: " + resultActivityPlan.get(400));
            return response;
        }
        ActivityPlan activityPlan = (ActivityPlan) resultActivityPlan.get(200);
        activityPlanInstitution.setIdActivity(activityPlan.getActivity().getIdActivity());
        Map<Integer, String> resultInstitutionPlan = saveInstitutionPlan(activityPlanInstitution);
        if (resultInstitutionPlan.containsKey(400)) {
            response.putAll(resultInstitutionPlan);
            return response;
        }
        response.put(200, "Actividad nueva agregada");
        return response;
    }

    @Override
    @Transactional
    public Map<Integer, String> deleteActivityWithInstitution(Long idActivityPlan) {
        Map<Integer, String> response = new HashMap<>();
        ActivityPlan activityPlan = activityPlanService.findById(idActivityPlan);
        if (activityPlan.getIdActivityPlan() == null) {
            response.put(400, "No se encontro el id proporcionado");
            return response;
        }
        Plan plan = planService.findById(activityPlan.getIdPlan());
        if (plan.getIdPlan() == null) {
            response.put(400, "Error al buscar un plan asignado a la tabla actividad_plan");
            return response;
        }
        if (!plan.getEditable()) {
            response.put(400, "Ya no se eliminan mas actividades pues ya no es editable");
            return response;
        }
        if (!periodDocentService.existsByIdDocentAndIdPeriod(plan.getIdDocent(), plan.getPeriod().getIdPeriod())) {
            response.put(400, "El docente o el periodo no pertenecen");
            return response;
        }
        Map<Integer, String> resultActivityPlan = deleteActivityPlanById(idActivityPlan);
        if (resultActivityPlan.containsKey(400)) {
            response.putAll(resultActivityPlan);
            return response;
        }
        Institution institution = institutionService.findInstitutionByActivity(activityPlan.getActivity().getIdActivity());
        if (institution.getIdInstitution() == null) {
            response.put(400, "No se ha encontrado una institucion a borrar");
            return response;
        }
        Map<Integer, String> resultInstitutionPlan = deleteInstitutionPlan(institution);
        if (resultInstitutionPlan.containsKey(400)) {
            response.putAll(resultInstitutionPlan);
            return response;
        }
        if (activityPlanService.findActivityPlansByIdPlan(plan.getIdPlan()).isEmpty()) {
            planService.deleteById(plan.getIdPlan());
        }
        response.put(200, "Actividad eliminada con exito");
        return response;
    }

    @Override
    @Transactional
    public Map<Integer, String> updateActivityWithInstitution(PlanInstitutionActivity activityPlanInstitution, Long
            idActivityPlan) {
        Map<Integer, String> response = new HashMap<>();
        Docent docent = docentService.findByIdPerson(activityPlanInstitution.getIdPerson());
        if (docent == null) {
            response.put(400, "Id de persona no encontrado");
            return response;
        }
        ActivityPlan activityPlan = activityPlanService.findById(idActivityPlan);
        if (activityPlan.getIdActivityPlan() == null) {
            response.put(400, "No se encontro la actividad a actualizar");
            return response;
        }
        Plan plan = planService.findById(activityPlan.getIdPlan());
        if (plan.getIdPlan() == null) {
            response.put(400, "Error al cargar el plan de las actividades");
            return response;
        }
        if (!plan.getEditable()) {
            response.put(400, "Ya no se actualizan mas actividades pues ya no es editable");
            return response;
        }
        if (periodService.findById(plan.getPeriod().getIdPeriod()).getState() == 3) {
            response.put(400, "Ya no se puede actualizar toda la actividad pues la etapa de registro ya paso");
            return response;
        }
        if (!periodDocentService.existsByIdDocentAndIdPeriod(docent.getIdDocent(), plan.getPeriod().getIdPeriod())) {
            response.put(400, "El docente o el periodo no pertenecen al actualizar la actividad");
            return response;
        }
        Map<Integer, String> validator = validateObjectPlanInstitutionActivity(activityPlanInstitution);
        if (validator.containsKey(400)) {
            response.putAll(validator);
            return response;
        }
        Map<Integer, String> resultActivityPlan = updateActivityPlan(activityPlanInstitution, idActivityPlan, plan);
        if (resultActivityPlan.containsKey(400)) {
            response.putAll(resultActivityPlan);
            return response;
        }
        activityPlanInstitution.setIdActivity(activityPlan.getActivity().getIdActivity());
        Institution institution = institutionService.findInstitutionByActivity(activityPlan.getActivity().getIdActivity());
        if (institution.getIdInstitution() == null) {
            response.put(400, "Error al econtrar la institucion con el id especificado, puede que solo la actividad se haya actualizado");
            return response;
        }
        Map<Integer, String> resultInstitutionPlan = updateInstitutionPlan(activityPlanInstitution, institution);
        if (resultInstitutionPlan.containsKey(400)) {
            response.putAll(resultInstitutionPlan);
            return response;
        }
        response.put(200, "Actividad actualizada con exito");
        return response;
    }

    @Override
    @Transactional
    public Map<Integer, String> validateActivitiesByIdActivityPlan(PlanInstitutionActivity
                                                                           activityPlanInstitution, Long idActivityPlan) {
        Map<Integer, String> response = new HashMap<>();
        ActivityPlan activityPlan = activityPlanService.findById(idActivityPlan);
        if (activityPlan.getIdActivityPlan() == null) {
            response.put(400, "Error al encontrar la actividad especificada");
            return response;
        }
        activityPlan.getActivity().setEvidences(activityPlanInstitution.getEvidences());
        activityPlanService.save(activityPlan);
        Institution institution = institutionService.findInstitutionByActivity(activityPlan.getActivity().getIdActivity());
        if (institution.getIdInstitution() == null) {
            response.put(400, "Error al buscar la institucion especificada con el id de la actividad");
            return response;
        }
        if (!institution.getInstitutionName().equals("Universidad Central del Ecuador")) {
            OtherInstitution otherInstitution = otherInstitutionService.findOtherInstitutionByInstitution(institution);
            otherInstitution.setVerificationLink(activityPlanInstitution.getVerificationLink());
            otherInstitutionService.save(otherInstitution);
        }
        response.put(200, "Validaciones enviadas correctamente");
        return response;
    }

    @Override
    @Transactional
    public Map<Integer, Object> findActivitiesPlanByPlan(Long idPerson, Long idPeriod) {
        List<Map<String, Object>> activitiesInstitutions = new ArrayList<>();
        Map<Integer, Object> response = new HashMap<>();
        Period period = periodService.findById(idPeriod);
        Plan plan = planService.findByIdPersonAndPeriod(idPerson, period);
        if (period.getIdPeriod() == null) {
            response.put(400, "Error al encontrar el periodo");
            return response;
        }
        if (plan.getIdPlan() == null) {
            plan.setPeriod(period);
            plan.setIdDocent(docentService.findByIdPerson(idPerson).getIdDocent());
            plan = planService.save(plan);
        }
        List<ActivityPlan> activityPlans = activityPlanService.findActivityPlansByIdPlan(plan.getIdPlan());
        for (ActivityPlan ap : activityPlans) {
            Long idActivity = ap.getActivity().getIdActivity();
            Institution institution = institutionService.findInstitutionByActivity(idActivity);
            Map<String, Object> activityInstitution = new LinkedHashMap<>();
            activityInstitution.put("activityPlan", ap);
            if (institution.getIdInstitution() == null) {
                response.put(400, "Error al encontrar la institucion a una actividad especifica ");
                return response;
            }
            OtherInstitution otherInstitution = otherInstitutionService
                    .findOtherInstitutionByInstitution(institution);
            UniversityInstitution universityInstitution = universityInstitutionService
                    .findUniversityInstitutionByInstitution(institution);
            if (otherInstitution.getIdOther() != null) {
                activityInstitution.put("institutionPlan", otherInstitution);
            } else {
                activityInstitution.put("institutionPlan", universityInstitution);
            }
            activitiesInstitutions.add(activityInstitution);
        }
        response.put(200, activitiesInstitutions);
        return response;
    }
    /*
        End of methods to register new activities
    */

    /*
        Methods to register just activityPlan
    */
    private Map<Integer, Object> saveActivityPlan(PlanInstitutionActivity activityPlanInstitution, Plan plan) {
        Map<Integer, Object> result = new HashMap<>();
        Type type = typeService.findById(activityPlanInstitution.getIdActivityType());
        Subtype subtype = subtypeService.findById(activityPlanInstitution.getIdActivitySubtype());
        Activity activity = activityService.save(new Activity(activityPlanInstitution.getDescriptionActivity(),
                activityPlanInstitution.getEvidences(),
                activityPlanInstitution.getStartDate(),
                activityPlanInstitution.getEndDate()));
        ActivityPlan activityPlan = activityPlanService.save(new ActivityPlan(plan.getIdPlan(),
                0, activity, type, subtype));
        if (type.getIdActivityType().intValue() == 2) {
            descriptionService.save(new Description(activityPlanInstitution.getDescriptionSubtype(), activityPlan));
        }
        result.put(200, activityPlan);
        return result;
    }

    private Map<Integer, String> deleteActivityPlanById(Long idActivityPlan) {
        Map<Integer, String> result = new HashMap<>();
        ActivityPlan activityPlan = activityPlanService.findById(idActivityPlan);
        if (activityPlan.getIdActivityPlan() == null) {
            result.put(400, "No se encontro una actividad plan correspondiente a ese id");
            return result;
        }
        if (descriptionService.existsByActivityPlan(activityPlan))
            descriptionService.deleteByActivityPlan(activityPlan);
        activityPlanService.delete(activityPlan);
        result.put(200, "Eliminado actividad plan correctamente");
        return result;
    }

    private Map<Integer, String> updateActivityPlan(PlanInstitutionActivity activityPlanInstitution, Long
            idActivityPlan, Plan plan) {
        Map<Integer, String> result = new HashMap<>();
        ActivityPlan activityPlan = activityPlanService.findById(idActivityPlan);
        if (activityPlan.getIdActivityPlan() == null) {
            result.put(400, "No se encontro una actividad plan correspondiente a ese id");
            return result;
        }
        Type type = typeService.findById(activityPlanInstitution.getIdActivityType());
        Subtype subtype = subtypeService.findById(activityPlanInstitution.getIdActivitySubtype());
        if (descriptionService.existsByActivityPlan(activityPlan)) {
            descriptionService.deleteByActivityPlan(activityPlan);
        }
        if (activityPlan.getType().getIdActivityType().intValue() == 2
                && descriptionService.findDescriptionByActivityPlan(activityPlan) != null) {
            descriptionService.save(new Description(activityPlanInstitution.getDescriptionSubtype(), activityPlan));
        }
        Activity activity = activityService.update(new Activity(activityPlanInstitution.getDescriptionActivity(),
                activityPlanInstitution.getEvidences(),
                activityPlanInstitution.getStartDate(),
                activityPlanInstitution.getEndDate()), activityPlan.getActivity().getIdActivity());
        activityPlanService.update(new ActivityPlan(plan.getIdPlan(),
                0, activity, type, subtype), activityPlan.getIdActivityPlan());
        result.put(200, "Actualizado actividad plan correctamente");
        return result;
    }
    /*
        End of methods to register just activityPlan
    */

    /*
        Methods to register just institutionPlan
    */
    private Map<Integer, String> saveInstitutionPlan(PlanInstitutionActivity activityPlanInstitution) {
        Map<Integer, String> result = new HashMap<>();
        if (activityPlanInstitution.getInstitutionName().equals("Universidad Central del Ecuador")) {
            University university = universityService.findById(activityPlanInstitution.getIdUniversity());
            Faculty faculty = facultyService.findById(activityPlanInstitution.getIdFaculty());
            Career career = careerService.findById(activityPlanInstitution.getIdCareer());
            Institution institution = institutionService.save(new Institution(activityPlanInstitution.getInstitutionName(),
                    activityPlanInstitution.getIdActivity()));
            universityInstitutionService.save(new UniversityInstitution(institution, university, faculty, career));
        } else {
            Institution institution = institutionService.save(new Institution(activityPlanInstitution.getInstitutionName(),
                    activityPlanInstitution.getIdActivity()));
            otherInstitutionService.save(new OtherInstitution(activityPlanInstitution.getOtherInstitutionName(),
                    activityPlanInstitution.getVerificationLink(), institution));
        }
        result.put(200, "Guardado institucion correctamente");
        return result;
    }

    private Map<Integer, String> deleteInstitutionPlan(Institution institution) {
        Map<Integer, String> result = new HashMap<>();
        OtherInstitution otherInstitution = otherInstitutionService.findOtherInstitutionByInstitution(institution);
        UniversityInstitution universityInstitution = universityInstitutionService.findUniversityInstitutionByInstitution(institution);
        if (otherInstitution.getIdOther() == null && universityInstitution.getIdUniversityInstitution() == null) {
            result.put(400, "La institucion no tiene valores asociades en otra institucion o universidad");
            return result;
        }
        if (otherInstitution.getIdOther() != null)
            otherInstitutionService.deleteOtherInstitutionByInstitution(institution);
        if (universityInstitution.getIdUniversityInstitution() != null)
            universityInstitutionService.deleteUniversityInstitutionByInstitution(institution);
        result.put(200, "Eliminado institucion correctamente");
        return result;
    }

    private Map<Integer, String> updateInstitutionPlan(PlanInstitutionActivity activityPlanInstitution, Institution
            institution) {
        Map<Integer, String> result = new HashMap<>();
        if (otherInstitutionService.findOtherInstitutionByInstitution(institution).getIdOther() != null) {
            otherInstitutionService.deleteOtherInstitutionByInstitution(institution);
        } else {
            universityInstitutionService.deleteUniversityInstitutionByInstitution(institution);
        }
        if (activityPlanInstitution.getInstitutionName().equals("Universidad Central del Ecuador")) {
            University university = universityService.findById(activityPlanInstitution.getIdUniversity());
            Faculty faculty = facultyService.findById(activityPlanInstitution.getIdFaculty());
            Career career = careerService.findById(activityPlanInstitution.getIdCareer());
            institution = institutionService.save(new Institution(activityPlanInstitution.getInstitutionName(),
                    activityPlanInstitution.getIdActivity()));
            universityInstitutionService.save(new UniversityInstitution(institution, university, faculty, career));
        } else {
            institution = institutionService.save(new Institution(activityPlanInstitution.getInstitutionName(),
                    activityPlanInstitution.getIdActivity()));
            otherInstitutionService.save(new OtherInstitution(activityPlanInstitution.getOtherInstitutionName(),
                    activityPlanInstitution.getVerificationLink(), institution));
        }
        result.put(200, "Actualizada institucion correctamente");
        return result;
    }
    /*
        End of methods to register just institutionPlan
    */

    private Map<Integer, String> validateObjectPlanInstitutionActivity(PlanInstitutionActivity activityPlanInstitution) {
        Map<Integer, String> result = new HashMap<>();
        Type type = typeService.findById(activityPlanInstitution.getIdActivityType());
        Subtype subtype = subtypeService.findById(activityPlanInstitution.getIdActivitySubtype());
        if (type.getIdActivityType() == null || subtype.getIdActivitySubtype() == null) {
            result.put(400, "Error al encontrar el tipo o subtipo especificados");
            return result;
        }
        if (!Objects.equals(subtype.getActivityType().getIdActivityType(), type.getIdActivityType())) {
            result.put(400, "El tipo no concuerda con el subtipo de actividad");
            return result;
        }
        if (activityPlanInstitution.getInstitutionName().equals("Universidad Central del Ecuador")) {
            University university = universityService.findById(activityPlanInstitution.getIdUniversity());
            Faculty faculty = facultyService.findById(activityPlanInstitution.getIdFaculty());
            Career career = careerService.findById(activityPlanInstitution.getIdCareer());
            if (university.getIdUniversity() == null || faculty.getIdFaculty() == null || career.getIdCareer() == null) {
                result.put(400, "Error al encontrar la universidad, facultad o carrera proporcionada");
                return result;
            }
            if (!Objects.equals(university.getIdUniversity(), faculty.getUniversity().getIdUniversity()) ||
                    !Objects.equals(faculty.getIdFaculty(), career.getFaculty().getIdFaculty())) {
                result.put(400, "No concuerda la carrera con la facultad, o la facultad con la carrera");
                return result;
            }
        }
        result.put(200, "Sin errores");
        return result;
    }

}
