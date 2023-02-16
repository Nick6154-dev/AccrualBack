package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.DatosDevengamiento;
import uce.edu.ec.devengamiento.models.entity.Docente;
import uce.edu.ec.devengamiento.models.service.IDatosDevengamientoService;
import uce.edu.ec.devengamiento.models.service.IDocenteService;
import uce.edu.ec.devengamiento.models.service.IMailService;

@Service
public class MailService implements IMailService {

    @Value("${enviroments.mailResponseFiniquito}")
    private String mail;

    @Autowired
    private IDocenteService docenteService;

    @Autowired
    private IDatosDevengamientoService datosDevengamientoService;

    @Override
    public SimpleMailMessage sendFiniquitoTrue(Long idDocente) {
        Docente docente = docenteService.findById(idDocente);
        DatosDevengamiento datosDevengamiento = datosDevengamientoService.findByIdDocente(idDocente);
        datosDevengamiento.setFiniquito(true);
        datosDevengamientoService.update(datosDevengamiento.getId(), datosDevengamiento);
        String nombreCompleto = docente.getNombres() + " " + docente.getApellidos();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail);
        message.setSubject("FINIQUITO: " + nombreCompleto);
        message.setText("El docente " + nombreCompleto + ", con numero de cedula "
                + docente.getCedula()
                + " ha solicitado que se aprueve el finiquito, "
                + "\n aceptando que su tiempo de devengamiento ha concluido.");
        return message;
    }
}
