package uce.edu.ec.accrualBack.service.objectService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrualBack.entity.AccrualData;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Person;
import uce.edu.ec.accrualBack.service.entityService.interfaces.AccrualDataService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DocentService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PersonService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.MailService;

@Service
public class MailServiceImpl implements MailService {

    @Value("${env.mailFrom}")
    private String mailFrom;

    @Autowired
    private DocentService docentService;

    @Autowired
    private AccrualDataService accrualDataService;

    @Autowired
    private PersonService personService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void setSettlementTrue(Long idPerson) {
        Docent docent = docentService.findByIdPerson(idPerson);
        Person person = personService.findById(idPerson);
        accrualDataService.updateSettlement(true, docent);
        String fullName = person.getName() + " " + person.getLastname();
        String message = "Por medio de la presente, me dirijo a usted para notificarle que el docente " +
                fullName + " ha presentado una solicitud de finiquito de su devengamiento para doctorados. " +
                "Dicha solicitud ha sido realizada de acuerdo con los procedimientos y requisitos establecidos por la " +
                "institución y las regulaciones vigentes.";
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailFrom);
        mailMessage.setTo("nikomont123@gmail.com");
        mailMessage.setSubject("Solicitud finiquito - " + fullName);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }

}
