package uce.edu.ec.devengamiento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.devengamiento.models.service.IMailService;

@RestController
@RequestMapping("/accrual/api/mail")
public class MailRest {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private IMailService mailService;

    @PostMapping("/approveFiniquito/{idDocente}")
    public void approveFiniquito(@PathVariable Long idDocente) {
        mailSender.send(mailService.sendFiniquitoTrue(idDocente));
    }

}
