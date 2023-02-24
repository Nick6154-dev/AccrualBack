package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrual.models.entity.*;
import uce.edu.ec.accrual.models.object.InstitutionActivity;
import uce.edu.ec.accrual.models.repository.*;
import uce.edu.ec.accrual.models.service.InstitutionActivityService;

import java.util.Objects;
import java.util.Optional;

@Service
public class InstitutionActivityServiceImpl implements InstitutionActivityService {

    @Autowired
    private CareerRepository careerService;

    @Autowired
    private FacultyRepository facultyService;

    @Autowired
    private InstitutionRepository institutionService;

    @Autowired
    private OtherInstitutionRepository otherInstitutionService;

    @Autowired
    private UniversityInstitutionRepository universityInstitutionService;

    @Autowired
    private UniversityRepository universityService;

    @Override
    @Transactional
    public ResponseEntity<?> save(InstitutionActivity institutionActivity) {
        if (institutionActivity.getInstitutionName().equals("Universidad Central del Ecuador")) {
            Optional<University> university = universityService.findById(institutionActivity.getIdUniversity());
            Optional<Faculty> faculty = facultyService.findById(institutionActivity.getIdFaculty());
            Optional<Career> career = careerService.findById(institutionActivity.getIdCareer());
            if (university.isPresent() && faculty.isPresent() && career.isPresent()) {
                if (Objects.equals(university.get().getIdUniversity(), faculty.get().getUniversity().getIdUniversity()) &&
                        Objects.equals(faculty.get().getIdFaculty(), career.get().getFaculty().getIdFaculty())) {
                    Institution institution = loadInstitution(institutionActivity, new Institution());
                    loadUniversityInstitution(institutionActivity, institution, new UniversityInstitution());
                    return ResponseEntity.status(HttpStatus.CREATED).body("Universidad guardada con exito");
                }
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Los tipos de universidad facultad y carrera " +
                        "no concuerdan entre ellos");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La universidad, facultad o carrera no han " +
                        "sido encontrados en el sistema");
            }
        } else {
            Institution institution = loadInstitution(institutionActivity, new Institution());
            loadOtherInstitution(institutionActivity, institution, new OtherInstitution());
            return ResponseEntity.status(HttpStatus.CREATED).body("Otra institucion guardada con exito");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteById(Long idInstitution) {
        return institutionService.findById(idInstitution).map(value -> {
            if (otherInstitutionService.findOtherInstitutionByInstitution(value).isPresent()) {
                otherInstitutionService.deleteOtherInstitutionByInstitution(value);
            } else {
                universityInstitutionService.deleteUniversityInstitutionByInstitution(value);
            }
            institutionService.delete(value);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado con exito");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se pudo encontrar un objeto" +
                " asignado a esa id"));
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(InstitutionActivity institutionActivity, Long idInstitution) {
        return institutionService.findById(idInstitution).map(value -> {
            if (!institutionActivity.getInstitutionName().equals(value.getInstitutionName())) {
                if (institutionActivity.getInstitutionName().equals("Universidad Central del Ecuador")) {
                    otherInstitutionService.deleteOtherInstitutionByInstitution(value);
                } else {
                    universityInstitutionService.deleteUniversityInstitutionByInstitution(value);
                }
            }
            if (institutionActivity.getInstitutionName().equals("Universidad Central del Ecuador")) {
                Optional<University> university = universityService.findById(institutionActivity.getIdUniversity());
                Optional<Faculty> faculty = facultyService.findById(institutionActivity.getIdFaculty());
                Optional<Career> career = careerService.findById(institutionActivity.getIdCareer());
                if (university.isPresent() && faculty.isPresent() && career.isPresent()) {
                    if (Objects.equals(university.get().getIdUniversity(), faculty.get().getUniversity().getIdUniversity()) &&
                            Objects.equals(faculty.get().getIdFaculty(), career.get().getFaculty().getIdFaculty())) {
                        Institution institution = loadInstitution(institutionActivity, value);
                        loadUniversityInstitution(institutionActivity, institution,
                                universityInstitutionService.findUniversityInstitutionByInstitution(institution)
                                        .orElse(new UniversityInstitution()));
                        return ResponseEntity.status(HttpStatus.CREATED).body("Datos actualizados con exito");
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Los tipos de universidad facultad y carrera " +
                                "no concuerdan entre ellos");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("La universidad, facultad o carrera no han " +
                            "sido encontrados en el sistema");
                }
            } else {
                Institution institution = loadInstitution(institutionActivity, value);
                loadOtherInstitution(institutionActivity, institution,
                        otherInstitutionService.findOtherInstitutionByInstitution(institution)
                                .orElse(new OtherInstitution()));
                return ResponseEntity.status(HttpStatus.CREATED).body("Datos actualizados con exito");
            }
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se pudo encontrar un objeto asignado " +
                "a esa id"));
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
        universityInstitution.setUniversity(universityService.findById(institutionActivity.getIdUniversity()).orElse(new University()));
        universityInstitution.setFaculty(facultyService.findById(institutionActivity.getIdFaculty()).orElse(new Faculty()));
        universityInstitution.setCareer(careerService.findById(institutionActivity.getIdCareer()).orElse(new Career()));
        universityInstitutionService.save(universityInstitution);
    }

}
