package uce.edu.ec.accrualBack.service.objectService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uce.edu.ec.accrualBack.entity.Docent;
import uce.edu.ec.accrualBack.entity.Period;
import uce.edu.ec.accrualBack.entity.Person;
import uce.edu.ec.accrualBack.service.entityService.interfaces.DocentService;
import uce.edu.ec.accrualBack.service.entityService.interfaces.PersonService;
import uce.edu.ec.accrualBack.service.objectService.interfaces.MailService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    @Value("${env.mailFrom}")
    private String mailFrom;

    @Value("${env.inProduction}")
    private boolean inProduction;

    @Autowired
    private DocentService docentService;

    @Autowired
    private PersonService personService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSettlementNotificationMail(Long idPerson) {
        Person person = personService.findById(idPerson);
        String fullName = person.getName() + " " + person.getLastname();
        String message = "Por medio de la presente, me dirijo a usted para notificarle que el docente " +
                fullName + " con C.I: " + person.getIdentification() + " ha presentado una solicitud de finiquito de su " +
                "devengamiento para doctorados.";
        String subject = "Solicitud finiquito - " + fullName;
        //Email to send is validator_role, should be the next function
        if (inProduction) {
            sendMail(message, subject, Arrays.asList("nikomont123@gmail.com", person.getEmail(), "mbgranjab@uce.edu.ec"));
        } else {
            sendMail(message, subject, Collections.singletonList("nikomont123@gmail.com"));
        }
    }

    @Override
    public void sendSettlementApproveMail(Long idPerson, boolean approved) {
        Person person = personService.findById(idPerson);
        String fullName = person.getName() + " " + person.getLastname();
        String response;
        if (approved) {
            response = "aprovado";
        } else {
            response = "denegado";
        }
        String message = "Por medio de la presente, me dirijo a usted para notificarle que su solicitud de finiquito de " +
                "devengamiento para doctorados ha sido " + response + ".";
        String subject = "Respuesta solicitud finiquito - " + fullName;
        //Email to send is validator_role, should be the next function
        if (inProduction) {
            sendMail(message, subject, Arrays.asList("nikomont123@gmail.com", person.getEmail(), "mbgranjab@uce.edu.ec"));
        } else {
            sendMail(message, subject, Collections.singletonList("nikomont123@gmail.com"));
        }
    }

    @Override
    public void sendPlanNotificationMail(Long idPerson) {
        Person person = personService.findById(idPerson);
        String fullName = person.getName() + " " + person.getLastname();
        String message = "Por medio de la presente, me dirijo a usted para notificarle formalmente que el docente " +
                fullName + " con C.I: " + person.getIdentification() + " ha completado y enviado todas las actividades " +
                "relacionadas con su devengamiento para su revisión.";
        String subject = "Actividades a revision - " + fullName;
        //Email to send is user_role, should be the next function
        if (inProduction) {
            sendMail(message, subject, Arrays.asList("nikomont123@gmail.com", person.getEmail(), "mbgranjab@uce.edu.ec"));
        } else {
            sendMail(message, subject, Collections.singletonList("nikomont123@gmail.com"));
        }
    }

    @Override
    public void sendStatePlanNotificationMail(Long idDocent, int state, String observations, Period period) {
        Docent docent = docentService.findById(idDocent);
        Person person = personService.findById(docent.getIdPerson());
        String fullName = person.getName() + " " + person.getLastname();
        String modePeriodString = "";
        String stateString = "negada";
        if (state == 1) {
            stateString = "aprobada";
        }
        if (period.getState() == 1) {
            modePeriodString = "etapa completa ";
        }
        if (period.getState() == 2) {
            modePeriodString = "etapa de registro ";
        }
        if (period.getState() == 3) {
            modePeriodString = "etapa de validacion ";
        }
        String message = "Estimado/a docente " + fullName + " con CI: " + person.getIdentification() + ",\n" +
                "\n" +
                "Me dirijo a usted con el propósito de informarle sobre la revisión del plan enviado" +
                " correspondiente al periodo " + period.getValuePeriod() + ". Dicho plan ha sido revisado y su estado " +
                "actual es " + modePeriodString + stateString + ". Además, me gustaría compartir las observaciones relevantes que se han identificado durante" +
                " esta revisión, las cuales son las siguientes: " + observations + ".\n" +
                "\n" +
                "Si desea obtener más información o discutir estos aspectos con mayor detalle, le invito a que se comunique" +
                " a través del correo institucional con la dirección de doctorados.";
        String subject = "Estado Plan " + period.getValuePeriod() + " - " + fullName;
        //Email to send is user_role, should be the next function
        if (inProduction) {
            sendMail(message, subject, Arrays.asList("nikomont123@gmail.com", person.getEmail(), "mbgranjab@uce.edu.ec"));
        } else {
            sendMail(message, subject, Collections.singletonList("nikomont123@gmail.com"));
        }
    }

    @Override
    public void sendNewUserNotificationMail(Long idPerson) {
        Person person = personService.findById(idPerson);
        String fullName = person.getName() + " " + person.getLastname();
        String message = "Estimado/a docente " + fullName + " con CI: " + person.getIdentification() + ",\n" +
                "\n" +
                "Nos complace informarle que su registro en nuestro sistema ha sido exitoso. Sus credenciales de acceso " +
                "son las siguientes: su nombre de usuario es su correo institucional (" + person.getEmail() + ") y su contraseña" +
                " es su número de cédula (" + person.getIdentification() + "). Estas credenciales le permitirán acceder a todas" +
                " las funcionalidades y recursos disponibles para los docentes en nuestra plataforma.";
        String subject = "Registro Sistema Devengamiento - " + fullName;
        //Email to send is validator_role, should be the next function
        if (inProduction) {
            sendMail(message, subject, Arrays.asList("nikomont123@gmail.com", person.getEmail(), "mbgranjab@uce.edu.ec"));
        } else {
            sendMail(message, subject, Collections.singletonList("nikomont123@gmail.com"));
        }
    }

    @Override
    public void sendNewDocentStateNotificationMail(Long idPerson) {
        Person person = personService.findById(idPerson);
        String fullName = person.getName() + " " + person.getLastname();
        String message = "Mediante esta comunicación, me dirijo a usted con el propósito de informarle" +
                " que el docente " + fullName + " con número de identificación " + person.getIdentification() + " ha presentado" +
                " una solicitud de ingreso al sistema. Le instamos a que acceda a la página correspondiente para revisar y tomar" +
                " una decisión sobre dicha solicitud, ya sea aprobándola o denegándola.";
        String subject = "Solicitud registro nuevo docente - " + fullName;
        //Email to send is validator_role, should be the next function
        if (inProduction) {
            sendMail(message, subject, Arrays.asList("nikomont123@gmail.com", person.getEmail(), "mbgranjab@uce.edu.ec"));
        } else {
            sendMail(message, subject, Collections.singletonList("nikomont123@gmail.com"));
        }
    }

    private void sendMail(String message, String subject, List<String> toMails) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailFrom);
        mailMessage.setTo(toMails.toArray(new String[0]));
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }

}
