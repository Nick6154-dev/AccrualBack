package uce.edu.ec.accrualBack.service.entityService.interfaces;

import uce.edu.ec.accrualBack.entity.Career;
import uce.edu.ec.accrualBack.entity.Faculty;

import java.util.List;

public interface CareerService {

    List<Career> findAll();

    Career findById(Long idCareer);

    List<Career> findCareersByFaculty(Faculty faculty);

    Career save(Career career);

    String delete(Career career);

    String deleteById(Long idCareer);

    Career update(Career career, Long idCareer);

}
