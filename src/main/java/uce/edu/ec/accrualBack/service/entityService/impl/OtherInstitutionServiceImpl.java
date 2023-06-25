package uce.edu.ec.accrualBack.service.entityService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.accrualBack.entity.Institution;
import uce.edu.ec.accrualBack.entity.OtherInstitution;
import uce.edu.ec.accrualBack.repository.OtherInstitutionRepository;
import uce.edu.ec.accrualBack.service.entityService.interfaces.OtherInstitutionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OtherInstitutionServiceImpl implements OtherInstitutionService {

    @Autowired
    private OtherInstitutionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<OtherInstitution> findAll() {
        return (List<OtherInstitution>) Optional.of(repository.findAll()).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional(readOnly = true)
    public OtherInstitution findById(Long idOtherInstitution) {
        return repository.findById(idOtherInstitution).orElseGet(OtherInstitution::new);
    }

    @Override
    @Transactional(readOnly = true)
    public OtherInstitution findOtherInstitutionByInstitution(Institution institution) {
        return repository.findOtherInstitutionByInstitution(institution).orElseGet(OtherInstitution::new);
    }

    @Override
    @Transactional
    public OtherInstitution save(OtherInstitution otherInstitution) {
        return repository.save(otherInstitution);
    }

    @Override
    @Transactional
    public String deleteById(Long idOtherInstitution) {
        return repository.findById(idOtherInstitution).map(value -> {
            repository.deleteById(idOtherInstitution);
            return "Eliminado con exito";
        }).orElseGet(() -> "No se pudo eliminar");
    }


    @Override
    @Transactional
    public void deleteOtherInstitutionByInstitution(Institution institution) {
        repository.deleteOtherInstitutionByInstitution(institution);
    }

    @Override
    @Transactional
    public OtherInstitution update(OtherInstitution otherInstitution, Long idOtherInstitution) {
        return repository.findById(idOtherInstitution).map(value -> {
            otherInstitution.setIdOther(idOtherInstitution);
            return repository.save(otherInstitution);
        }).orElseGet(OtherInstitution::new);
    }
}
