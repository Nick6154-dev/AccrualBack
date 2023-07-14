package uce.edu.ec.accrualBack.service.objectService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrualBack.entity.*;
import uce.edu.ec.accrualBack.object.PlanInstitutionActivity;
import uce.edu.ec.accrualBack.service.entityService.interfaces.*;
import uce.edu.ec.accrualBack.service.objectService.interfaces.PlanInstitutionActivityService;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

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
    public String addNewActivityWithInstitution(PlanInstitutionActivity activityPlanInstitution) {
        Docent docent = docentService.findByIdPerson(activityPlanInstitution.getIdPerson());
        if (docent.getIdDocent() != null) {
            Period period = periodService.findById(activityPlanInstitution.getIdPeriod());
            Plan plan = planService.findByIdPersonAndPeriod(activityPlanInstitution.getIdPerson(), period);
            if (plan.getIdPlan() == null) {
                plan.setPeriod(period);
                plan.setIdDocent(docent.getIdDocent());
                plan.setStarDate(LocalDate.now());
                plan.setEditable(true);
                plan = planService.save(plan);
            }
            if (!plan.getEditable()) return "Ya no se agregan mas actividades pues ya no es editable";
            if (!plan.getPeriod().getActive()) return "Ya no se pueden agregar mas actividades, el periodo ya no esta activo";
            if (!plan.getPeriod().getState()) return "Ya no se pueden agregar mas actividades, la etapa de registro ya paso";
            activityPlanInstitution.setIdPlan(plan.getIdPlan());
            ActivityPlan activityPlan = saveActivityPlan(activityPlanInstitution);
            if (activityPlan == null) {
                return "Problemas al cargar el plan activity";
            }
            activityPlanInstitution.setIdActivity(activityPlan.getActivity().getIdActivity());
            saveInstitutionPlan(activityPlanInstitution);
            return "Actividad nueva agregada";
        }
        return "El id no pertenece a ningun docente";
    }

    @Override
    public String deleteActivityWithInstitution(Long idActivityPlan) {
        ActivityPlan activityPlan = activityPlanService.findById(idActivityPlan);
        if (activityPlan != null) {
            Plan plan = planService.findById(activityPlan.getIdPlan());
            if (plan != null) {
                if (!plan.getEditable()) {
                    return "Ya no se eliminan mas actividades pues ya no es editable";
                }
            }
            String response = deleteActivityPlanById(idActivityPlan);
            Optional<Institution> institution = Optional.of(institutionService
                    .findInstitutionByActivity(activityPlan.getActivity().getIdActivity()));
            institution.ifPresent(value -> deleteActivityPlanById(value.getIdInstitution()));
            return response;
        }
        return "Problemas al eliminar";
    }

    @Override
    public String updateActivityWithInstitution(PlanInstitutionActivity activityPlanInstitution, Long idActivityPlan) {
        Docent docent = docentService.findByIdPerson(activityPlanInstitution.getIdPerson());
        if (docent != null) {
            ActivityPlan activityPlan = activityPlanService.findById(idActivityPlan);
            if (activityPlan != null) {
                Plan plan = planService.findById(activityPlan.getIdPlan());
                if (plan != null) {
                    if (!plan.getEditable()) {
                        return "Ya no se actualizan mas actividades pues ya no es editable";
                    }
                }
                activityPlanInstitution.setIdPlan(activityPlan.getIdPlan());
                String response = updateActivityPlan(activityPlanInstitution, idActivityPlan);
                activityPlanInstitution.setIdActivity(activityPlan.getActivity().getIdActivity());
                Optional<Institution> institution = Optional.of(institutionService.findInstitutionByActivity(activityPlan.getActivity().getIdActivity()));
                institution.ifPresent(value -> updateInstitutionPlan(activityPlanInstitution, value.getIdInstitution()));
                return response;
            }
            return "Problemas al actualizar";
        }
        return "Id de persona no encontrado";
    }

    @Override
    public String validateActivitiesByIdActivityPlan(PlanInstitutionActivity activityPlanInstitution, Long idActivityPlan) {
        ActivityPlan activityPlan = activityPlanService.findById(idActivityPlan);
        activityPlan.getActivity().setEvidences(activityPlanInstitution.getEvidences());
        activityPlanService.save(activityPlan);
        Institution institution = institutionService.findInstitutionByActivity(activityPlan.getActivity().getIdActivity());
        if (!institution.getInstitutionName().equals("Universidad Central del Ecuador")) {
            OtherInstitution otherInstitution = otherInstitutionService.findOtherInstitutionByInstitution(institution);
            otherInstitution.setVerificationLink(activityPlanInstitution.getVerificationLink());
            otherInstitutionService.save(otherInstitution);
        }
        return "Validaciones enviadas correctamente";
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
            if (descriptionService.existsByActivityPlan(activityPlan)) {
                descriptionService.deleteByActivityPlan(activityPlan);
            }
            activityService.delete(activityPlan.getActivity());
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

    private void updateInstitutionPlan(PlanInstitutionActivity activityPlanInstitution, Long idInstitution) {
        Institution institution = institutionService.findById(idInstitution);
        if (!activityPlanInstitution.getInstitutionName().equals(institution.getInstitutionName())) {
            if (activityPlanInstitution.getInstitutionName().equals("Universidad Central del Ecuador")) {
                otherInstitutionService.deleteOtherInstitutionByInstitution(institution);
            } else {
                universityInstitutionService.deleteUniversityInstitutionByInstitution(institution);
            }
        }
        if (activityPlanInstitution.getInstitutionName().equals("Universidad Central del Ecuador")) {
            University university = universityService.findById(activityPlanInstitution.getIdUniversity());
            Faculty faculty = facultyService.findById(activityPlanInstitution.getIdFaculty());
            Career career = careerService.findById(activityPlanInstitution.getIdCareer());
            if (university != null && faculty != null && career != null) {
                if (Objects.equals(university.getIdUniversity(), faculty.getUniversity().getIdUniversity()) &&
                        Objects.equals(faculty.getIdFaculty(), career.getFaculty().getIdFaculty())) {
                    Institution institutionSave = loadInstitution(activityPlanInstitution, institution);
                    loadUniversityInstitution(activityPlanInstitution, institutionSave,
                            universityInstitutionService.findUniversityInstitutionByInstitution(institutionSave));
                }
            }
        } else {
            Institution institutionSave = loadInstitution(activityPlanInstitution, institution);
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
