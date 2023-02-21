package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrual.models.service.AccrualDataService;
import uce.edu.ec.accrual.models.service.DocentService;

@Service
public class MailService implements uce.edu.ec.accrual.models.service.MailService {

    @Value("${enviroments.mailResponseFiniquito}")
    private String mail;

    @Autowired
    private DocentService docenteService;

    @Autowired
    private AccrualDataService datosDevengamientoService;

    @Override
    public SimpleMailMessage sendFiniquitoTrue(Long idDocent) {
        return null;
    }
}
