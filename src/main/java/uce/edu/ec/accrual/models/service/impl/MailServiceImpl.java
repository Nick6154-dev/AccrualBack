package uce.edu.ec.accrual.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrual.models.service.AccrualDataService;
import uce.edu.ec.accrual.models.service.DocentService;
import uce.edu.ec.accrual.models.service.MailService;

@Service
public class MailServiceImpl implements MailService {

    @Value("${enviroments.mailResponseFiniquito}")
    private String mail;

    @Autowired
    private DocentService docenteService;

    @Autowired
    private AccrualDataService datosDevengamientoService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendFiniquitoTrue(String receiver, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receiver);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

}
