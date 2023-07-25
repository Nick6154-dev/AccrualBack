package uce.edu.ec.accrualBack.service.objectService.interfaces;

import uce.edu.ec.accrualBack.entity.Period;

public interface MailService {

    void sendSettlementNotificationMail(Long idPerson);

    void sendSettlementApproveMail(Long idPerson, boolean approved);

    void sendPlanNotificationMail(Long idPerson);

    void sendStatePlanNotificationMail(Long idDocent, int state, String observations, Period period);

    void sendNewUserNotificationMail(Long idPerson);

    void sendNewDocentStateNotificationMail(Long idPerson);

}
