package uce.edu.ec.accrualBack.service.objectService.interfaces;

public interface MailService {

    void sendSettlementNotificationMail(Long idPerson);

    void sendSettlementApproveMail(Long idPerson, boolean approved);

    void sendPlanNotificationMail(Long idPerson);

    void sendStatePlanNotificationMail(Long idDocent, Long state, String observations, String period);

    void sendNewUserNotificationMail(Long idPerson);

    void sendNewDocentStateNotificationMail(Long idPerson);

}
