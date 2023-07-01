package uce.edu.ec.accrualBack.service.objectService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.*;
import uce.edu.ec.accrualBack.object.InstitutionActivity;
import uce.edu.ec.accrualBack.service.entityService.interfaces.*;
import uce.edu.ec.accrualBack.service.objectService.interfaces.InstitutionActivityService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.UniversityInstitutionService;

import java.util.Objects;
import java.util.Optional;

@Service
public class InstitutionActivityServiceImpl implements InstitutionActivityService {

    @Autowired
    private CareerService careerService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private OtherInstitutionService otherInstitutionService;

    @Autowired
    private UniversityInstitutionService universityInstitutionService;

    @Autowired
    private UniversityService universityService;

    @Override
    @Transactional
    public String save(InstitutionActivity institutionActivity) {
        if (institutionActivity.getInstitutionName().equals("Universidad Central del Ecuador")) {
            University university = universityService.findById(institutionActivity.getIdUniversity());
            Faculty faculty = facultyService.findById(institutionActivity.getIdFaculty());
            Career career = careerService.findById(institutionActivity.getIdCareer());
            if (university != null && faculty != null && career != null) {
                if (Objects.equals(university.getIdUniversity(), faculty.getUniversity().getIdUniversity()) &&
                        Objects.equals(faculty.getIdFaculty(), career.getFaculty().getIdFaculty())) {
                    Institution institution = loadInstitution(institutionActivity, new Institution());
                    loadUniversityInstitution(institutionActivity, institution, new UniversityInstitution());
                    return "Universidad guardada con exito";
                }
                return "Los tipos de universidad facultad y carrera no concuerdan entre ellos";
            } else {
                return "La universidad, facultad o carrera no han sido encontrados en el sistema";
            }
        } else {
            Institution institution = loadInstitution(institutionActivity, new Institution());
            loadOtherInstitution(institutionActivity, institution, new OtherInstitution());
            return "Otra institucion guardada con exito";
        }
    }

    @Override
    @Transactional
    public String deleteById(Long idInstitution) {
        return Optional.of(institutionService.findById(idInstitution)).map(value -> {
            if (otherInstitutionService.findOtherInstitutionByInstitution(value).getIdOther() != null) {
                otherInstitutionService.deleteOtherInstitutionByInstitution(value);
            } else {
                universityInstitutionService.deleteUniversityInstitutionByInstitution(value);
            }
            institutionService.delete(value);
            return "Eliminado con exito";
        }).orElse("No se pudo encontrar un objeto asignado a esa id");
    }

    @Override
    @Transactional
    public String update(InstitutionActivity institutionActivity, Long idInstitution) {
        return Optional.of(institutionService.findById(idInstitution)).map(value -> {
            if (!institutionActivity.getInstitutionName().equals(value.getInstitutionName())) {
                if (institutionActivity.getInstitutionName().equals("Universidad Central del Ecuador")) {
                    otherInstitutionService.deleteOtherInstitutionByInstitution(value);
                } else {
                    universityInstitutionService.deleteUniversityInstitutionByInstitution(value);
                }
            }
            if (institutionActivity.getInstitutionName().equals("Universidad Central del Ecuador")) {
                University university = universityService.findById(institutionActivity.getIdUniversity());
                Faculty faculty = facultyService.findById(institutionActivity.getIdFaculty());
                Career career = careerService.findById(institutionActivity.getIdCareer());
                if (university != null && faculty != null && career != null) {
                    if (Objects.equals(university.getIdUniversity(), faculty.getUniversity().getIdUniversity()) &&
                            Objects.equals(faculty.getIdFaculty(), career.getFaculty().getIdFaculty())) {
                        Institution institution = loadInstitution(institutionActivity, value);
                        loadUniversityInstitution(institutionActivity, institution,
                                universityInstitutionService.findUniversityInstitutionByInstitution(institution));
                        return "Datos actualizados con exito";
                    } else {
                        return "Los tipos de universidad facultad y carrera no concuerdan entre ellos";
                    }
                } else {
                    return "La universidad, facultad o carrera no han sido encontrados en el sistema";
                }
            } else {
                Institution institution = loadInstitution(institutionActivity, value);
                loadOtherInstitution(institutionActivity, institution,
                        otherInstitutionService.findOtherInstitutionByInstitution(institution));
                return "Datos actualizados con exito";
            }
        }).orElse("No se pudo encontrar un objeto asignado a esa id");
    }

    @Transactional
    public Institution loadInstitution(InstitutionActivity institutionActivity, Institution institution) {
        institution.setIdActivity(institutionActivity.getIdActivity());
        institution.setInstitutionName(institutionActivity.getInstitutionName());
        return institutionService.save(institution);
    }

    @Transactional
    public void loadOtherInstitution(InstitutionActivity institutionActivity, Institution institution,
                                     OtherInstitution otherInstitution) {
        otherInstitution.setOtherName(institutionActivity.getOtherInstitutionName());
        otherInstitution.setInstitution(institution);
        otherInstitution.setVerificationLink(institutionActivity.getVerificationLink());
        otherInstitutionService.save(otherInstitution);
    }

    @Transactional
    public void loadUniversityInstitution(InstitutionActivity institutionActivity, Institution institution,
                                          UniversityInstitution universityInstitution) {
        universityInstitution.setInstitution(institution);
        universityInstitution.setUniversity(universityService.findById(institutionActivity.getIdUniversity()));
        universityInstitution.setFaculty(facultyService.findById(institutionActivity.getIdFaculty()));
        universityInstitution.setCareer(careerService.findById(institutionActivity.getIdCareer()));
        universityInstitutionService.save(universityInstitution);
    }

}
