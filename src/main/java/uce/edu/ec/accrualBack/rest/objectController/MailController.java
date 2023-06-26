package uce.edu.ec.accrualBack.rest.objectController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uce.edu.ec.accrualBack.service.objectService.interfaces.MailService;

@RestController
@RequestMapping("/accrual/api/mail")
public class MailController {

    @Value("${env.mailToTest}")
    private String mailTo;

    @Autowired
    private MailService mailService;

    @PostMapping("/approveFiniquito/{idDocente}")
    public void approveFiniquito(@PathVariable Long idDocente) {
        //mailService.sendFiniquitoTrue(idDocente);
    }

    @PostMapping("/test")
    public void test() {
        mailService.sendFiniquitoTrue(mailTo, "ola", "ola");
    }

}
