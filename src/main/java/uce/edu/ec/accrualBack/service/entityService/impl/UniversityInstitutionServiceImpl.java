package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Institution;
import uce.edu.ec.accrualBack.entity.UniversityInstitution;
import uce.edu.ec.accrualBack.repository.UniversityInstitutionRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.UniversityInstitutionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UniversityInstitutionServiceImpl implements UniversityInstitutionService {

    @Autowired
    private UniversityInstitutionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<UniversityInstitution> findAll() {
        return (List<UniversityInstitution>) Optional.of(repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public UniversityInstitution findById(Long idUniversityInstitution) {
        return repository.findById(idUniversityInstitution).orElseGet(UniversityInstitution::new);
    }

    @Override
    @Transactional(readOnly = true)
    public UniversityInstitution findUniversityInstitutionByInstitution(Institution institution) {
        return repository.findUniversityInstitutionByInstitution(institution).orElseGet(UniversityInstitution::new);
    }

    @Override
    @Transactional
    public UniversityInstitution save(UniversityInstitution universityInstitution) {
        return repository.save(universityInstitution);
    }

    @Override
    @Transactional
    public String deleteById(Long idUniversityInstitution) {
        return repository.findById(idUniversityInstitution).map(value -> {
            repository.deleteById(idUniversityInstitution);
            return "Eliminado con exito";
        }).orElseGet(() -> "No se ha podido eliminar");
    }

    @Override
    @Transactional
    public void deleteUniversityInstitutionByInstitution(Institution institution) {
        repository.deleteUniversityInstitutionByInstitution(institution);
    }

    @Override
    @Transactional
    public UniversityInstitution update(UniversityInstitution universityInstitution, Long idUniversityInstitution) {
        return repository.findById(idUniversityInstitution).map(value -> {
            universityInstitution.setIdUniversityInstitution(idUniversityInstitution);
            return repository.save(universityInstitution);
        }).orElseGet(UniversityInstitution::new);
    }
}
