package uce.edu.ec.accrual.models.service;

import org.springframework.mail.SimpleMailMessage;

public interface MailService {

    SimpleMailMessage sendFiniquitoTrue(Long idDocente);

}
