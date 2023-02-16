package uce.edu.ec.devengamiento.models.service;

import org.springframework.mail.SimpleMailMessage;

public interface IMailService {

    SimpleMailMessage sendFiniquitoTrue(Long idDocente);

}
