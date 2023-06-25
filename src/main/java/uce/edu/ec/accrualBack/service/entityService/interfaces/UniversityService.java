package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.University;

import java.util.List;

public interface UniversityService {

    List<University> findAll();

    University findById(Long idUniversity);

    University save(University university);

    String delete(University university);

    String deleteById(Long idUniversity);

    University update(University university, Long idUniversity);

}
