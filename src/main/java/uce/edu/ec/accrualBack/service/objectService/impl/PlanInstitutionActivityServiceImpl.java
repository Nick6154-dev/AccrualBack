package uce.edu.ec.accrualBack.service.objectService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.*;
import uce.edu.ec.accrualBack.object.PlanInstitutionActivity;
import uce.edu.ec.accrualBack.service.entityService.interfaces.*;
import uce.edu.ec.accrualBack.service.objectService.interfaces.PlanInstitutionActivityService;

import java.sql.Time;
import java.time.LocalDate;
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

    /*
        Methods to register new activities
    */
    @Override
    @Transactional
    public Map<Integer, String> addNewActivityWithInstitution(PlanInstitutionActivity activityPlanInstitution) {
        Map<Integer, String> response = new HashMap<>();
        Docent docent = docentService.findByIdPerson(activityPlanInstitution.getIdPerson());
        if (docent.getIdDocent() != null) {
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
            if (!plan.getPeriod().getState()) {
                response.put(400, "Ya no se pueden agregar mas actividades, la etapa de registro ya paso");
                return response;
            }
            activityPlanInstitution.setIdPlan(plan.getIdPlan());
            ActivityPlan activityPlan = saveActivityPlan(activityPlanInstitution);
            if (activityPlan.getIdActivityPlan() == null) {
                response.put(400, "Problemas al guardar el plan activity");
                return response;
            }
            activityPlanInstitution.setIdActivity(activityPlan.getActivity().getIdActivity());
            saveInstitutionPlan(activityPlanInstitution);
            response.put(200, "Actividad nueva agregada");
            return response;
        }
        response.put(400, "El id no pertenece a ningun docente");
        return response;
    }

    @Override
    @Transactional
    public Map<Integer, String> deleteActivityWithInstitution(Long idActivityPlan) {
        Map<Integer, String> response = new HashMap<>();
        ActivityPlan activityPlan = activityPlanService.findById(idActivityPlan);
        if (activityPlan.getIdActivityPlan() != null) {
            Plan plan = planService.findById(activityPlan.getIdPlan());
            if (plan.getIdPlan() == null) {
                response.put(400, "Error al buscar un plan asignado a la tabla actividad_plan");
                return response;
            }
            if (!plan.getEditable()) {
                response.put(400, "Ya no se eliminan mas actividades pues ya no es editable");
                return response;
            }
            deleteActivityPlanById(idActivityPlan);
            Optional<Institution> institution = Optional.of(institutionService
                    .findInstitutionByActivity(activityPlan.getActivity().getIdActivity()));
            institution.ifPresent(this::deleteInstitutionPlan);
            response.put(200, "Actividad eliminada con exito");
            return response;
        }
        response.put(400, "No se encontro el id proporcionado");
        return response;
    }

    @Override
    @Transactional
    public Map<Integer, String> updateActivityWithInstitution(PlanInstitutionActivity activityPlanInstitution, Long idActivityPlan) {
        Map<Integer, String> response = new HashMap<>();
        Docent docent = docentService.findByIdPerson(activityPlanInstitution.getIdPerson());
        if (docent != null) {
            ActivityPlan activityPlan = activityPlanService.findById(idActivityPlan);
            if (activityPlan != null) {
                Plan plan = planService.findById(activityPlan.getIdPlan());
                if (plan.getIdPlan() == null) {
                    response.put(400, "Error al cargar el plan de las actividades");
                    return response;
                }
                if (!plan.getEditable()) {
                    response.put(400, "Ya no se actualizan mas actividades pues ya no es editable");
                    return response;
                }
                activityPlanInstitution.setIdPlan(activityPlan.getIdPlan());
                updateActivityPlan(activityPlanInstitution, idActivityPlan);
                activityPlanInstitution.setIdActivity(activityPlan.getActivity().getIdActivity());
                Institution institution = institutionService.findInstitutionByActivity(activityPlan.getActivity().getIdActivity());
                if (institution.getIdInstitution() == null) {
                    response.put(400, "Error al econtrar la institucion con el id especificado, puede que la actividad se haya actualizado");
                    return response;
                }
                updateInstitutionPlan(activityPlanInstitution, institution);
                response.put(200, "Actividad actualizada con exito");
                return response;
            }
            response.put(400, "Problemas al actualizar");
            return response;
        }
        response.put(400, "Id de persona no encontrado");
        return response;
    }

    @Override
    @Transactional
    public Map<Integer, String> validateActivitiesByIdActivityPlan(PlanInstitutionActivity activityPlanInstitution, Long idActivityPlan) {
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
    private ActivityPlan saveActivityPlan(PlanInstitutionActivity activityPlanInstitution) {
        Type type = typeService.findById(activityPlanInstitution.getIdActivityType());
        Subtype subtype = subtypeService.findById(activityPlanInstitution.getIdActivitySubtype());
        Plan plan = planService.findById(activityPlanInstitution.getIdPlan());
        if (plan != null) {
            if (type != null && subtype != null) {
                if (!Objects.equals(subtype.getActivityType().getIdActivityType(), type.getIdActivityType())) {
                    return new ActivityPlan();
                }
                Activity activity = loadActivity(activityPlanInstitution, new Activity());
                ActivityPlan activityPlan = loadPlanAccrual(activityPlanInstitution, activity, type, subtype, new ActivityPlan());
                if (type.getIdActivityType().intValue() == 2) {
                    loadDescription(activityPlanInstitution, activityPlan, new Description());
                }
                return activityPlan;
            } else {
                return new ActivityPlan();
            }
        }
        return new ActivityPlan();
    }

    private String deleteActivityPlanById(Long idActivityPlan) {
        return Optional.of(activityPlanService.findById(idActivityPlan)).map(activityPlan -> {
            if (descriptionService.existsByActivityPlan(activityPlan)) descriptionService.deleteByActivityPlan(activityPlan);
            activityPlanService.delete(activityPlan);
            return "Eliminado con exito";
        }).orElse("El id especificado no se encuentra en el sistema");
    }

    private String updateActivityPlan(PlanInstitutionActivity activityPlanInstitution, Long idActivityPlan) {
        return Optional.of(activityPlanService.findById(idActivityPlan)).map(activityPlan -> {
            if (typeService.findById(activityPlanInstitution.getIdActivityType()) != null &&
                    subtypeService.findById(activityPlanInstitution.getIdActivitySubtype()) != null) {
                if (!Objects.equals(subtypeService.findById(
                                activityPlanInstitution.getIdActivitySubtype()).getActivityType().getIdActivityType(),
                        typeService.findById(activityPlanInstitution.getIdActivityType()).getIdActivityType())) {
                    return "El subtipo de actividad no pertenece";
                }
                if (descriptionService.existsByActivityPlan(activityPlan)) {
                    descriptionService.deleteByActivityPlan(activityPlan);
                }
                if (activityPlan.getType().getIdActivityType().intValue() == 2
                        && descriptionService.findDescriptionByActivityPlan(activityPlan) != null) {
                    loadDescription(activityPlanInstitution, activityPlan, descriptionService.findDescriptionByActivityPlan(activityPlan));
                }
                loadPlanAccrual(activityPlanInstitution, loadActivity(activityPlanInstitution, activityPlan.getActivity()),
                        typeService.findById(activityPlanInstitution.getIdActivityType()),
                        subtypeService.findById(activityPlanInstitution.getIdActivitySubtype()), activityPlan);
                return "Actividad actualizada correctamente";
            } else {
                return "Problemas al cargar el tipo o subtipo enviados";
            }
        }).orElse("El id especificado no se encuentra en el sistema");
    }

    private Activity loadActivity(PlanInstitutionActivity activityPlanInstitution, Activity activity) {
        activity.setStartDate(activityPlanInstitution.getStarDate());
        activity.setEndDate(activityPlanInstitution.getEndDate());
        activity.setDescription(activityPlanInstitution.getDescriptionActivity());
        activity.setEvidences("Etapa de registro");
        return activityService.save(activity);
    }

    private ActivityPlan loadPlanAccrual(PlanInstitutionActivity activityPlanInstitution, Activity activity,
                                         Type type, Subtype subtype, ActivityPlan activityPlan) {
        activityPlan.setIdPlan(activityPlanInstitution.getIdPlan());
        activityPlan.setActivity(activity);
        activityPlan.setType(type);
        activityPlan.setSubtype(subtype);
        activityPlan.setState(0);
        return activityPlanService.save(activityPlan);
    }

    private void loadDescription(PlanInstitutionActivity activityPlanInstitution, ActivityPlan activityPlan, Description description) {
        description.setActivityPlan(activityPlan);
        description.setDescription(activityPlanInstitution.getDescriptionSubtype());
        descriptionService.save(description);
    }
    /*
        End of methods to register just activityPlan
    */

    /*
        Methods to register just institutionPlan
    */
    private void saveInstitutionPlan(PlanInstitutionActivity activityPlanInstitution) {
        if (activityPlanInstitution.getInstitutionName().equals("Universidad Central del Ecuador")) {
            University university = universityService.findById(activityPlanInstitution.getIdUniversity());
            Faculty faculty = facultyService.findById(activityPlanInstitution.getIdFaculty());
            Career career = careerService.findById(activityPlanInstitution.getIdCareer());
            if (university != null && faculty != null && career != null) {
                if (Objects.equals(university.getIdUniversity(), faculty.getUniversity().getIdUniversity()) &&
                        Objects.equals(faculty.getIdFaculty(), career.getFaculty().getIdFaculty())) {
                    Institution institution = loadInstitution(activityPlanInstitution, new Institution());
                    loadUniversityInstitution(activityPlanInstitution, institution, new UniversityInstitution());
                }
            }
        } else {
            Institution institution = loadInstitution(activityPlanInstitution, new Institution());
            loadOtherInstitution(activityPlanInstitution, institution, new OtherInstitution());
        }
    }

    private void deleteInstitutionPlan(Institution institution) {
        OtherInstitution otherInstitution = otherInstitutionService.findOtherInstitutionByInstitution(institution);
        UniversityInstitution universityInstitution = universityInstitutionService.findUniversityInstitutionByInstitution(institution);
        if (otherInstitution.getIdOther() != null) otherInstitutionService.deleteOtherInstitutionByInstitution(institution);
        if (universityInstitution.getIdUniversityInstitution() != null) universityInstitutionService.deleteUniversityInstitutionByInstitution(institution);
    }

    private void updateInstitutionPlan(PlanInstitutionActivity activityPlanInstitution, Institution institution) {
        if (otherInstitutionService.findOtherInstitutionByInstitution(institution).getIdOther() != null) {
            otherInstitutionService.deleteOtherInstitutionByInstitution(institution);
        } else {
            universityInstitutionService.deleteUniversityInstitutionByInstitution(institution);
        }
        if (activityPlanInstitution.getInstitutionName().equals("Universidad Central del Ecuador")) {
            University university = universityService.findById(activityPlanInstitution.getIdUniversity());
            Faculty faculty = facultyService.findById(activityPlanInstitution.getIdFaculty());
            Career career = careerService.findById(activityPlanInstitution.getIdCareer());
            if (university != null && faculty != null && career != null) {
                if (Objects.equals(university.getIdUniversity(), faculty.getUniversity().getIdUniversity()) &&
                        Objects.equals(faculty.getIdFaculty(), career.getFaculty().getIdFaculty())) {
                    Institution institutionSave = loadInstitution(activityPlanInstitution, new Institution());
                    loadUniversityInstitution(activityPlanInstitution, institutionSave,
                            universityInstitutionService.findUniversityInstitutionByInstitution(institutionSave));
                }
            }
        } else {
            Institution institutionSave = loadInstitution(activityPlanInstitution, new Institution());
            loadOtherInstitution(activityPlanInstitution, institutionSave,
                    otherInstitutionService.findOtherInstitutionByInstitution(institutionSave));
        }
    }

    private Institution loadInstitution(PlanInstitutionActivity activityPlanInstitution, Institution institution) {
        institution.setIdActivity(activityPlanInstitution.getIdActivity());
        institution.setInstitutionName(activityPlanInstitution.getInstitutionName());
        return institutionService.save(institution);
    }

    private void loadOtherInstitution(PlanInstitutionActivity activityPlanInstitution, Institution institution,
                                      OtherInstitution otherInstitution) {
        otherInstitution.setOtherName(activityPlanInstitution.getOtherInstitutionName());
        otherInstitution.setInstitution(institution);
        otherInstitution.setVerificationLink("Etapa de registro");
        otherInstitutionService.save(otherInstitution);
    }

    private void loadUniversityInstitution(PlanInstitutionActivity activityPlanInstitution, Institution institution,
                                           UniversityInstitution universityInstitution) {
        universityInstitution.setInstitution(institution);
        universityInstitution.setUniversity(universityService.findById(activityPlanInstitution.getIdUniversity()));
        universityInstitution.setFaculty(facultyService.findById(activityPlanInstitution.getIdFaculty()));
        universityInstitution.setCareer(careerService.findById(activityPlanInstitution.getIdCareer()));
        universityInstitutionService.save(universityInstitution);
    }
    /*
        End of methods to register just institutionPlan
    */

}
