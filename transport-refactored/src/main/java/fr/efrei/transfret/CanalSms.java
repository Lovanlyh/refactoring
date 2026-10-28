package fr.efrei.transfret;

public class CanalSms implements CanalNotification {

    private final SmsService smsService = new SmsService();

    @Override
    public void envoyer(
            String destinataire,
            String sujet,
            String message) {

        smsService.envoyerSms(destinataire, message);
    }
}
