package fr.efrei.transfret;

public class Notificateur {

    private final CanalNotification canalEmail;
    private final CanalNotification canalSms;

    public Notificateur(CanalNotification canalEmail, CanalNotification canalSms) {

        this.canalEmail = canalEmail;
        this.canalSms = canalSms;
    }

    public Notificateur() {
        this(new CanalEmail(), new CanalSms());
    }

    public void notifier(Client client, String sujet, String corps, String sms) {

        canalEmail.envoyer(client.getEmail(), sujet, corps);

        if (client.getTypeClient() == TypeClient.PREMIUM) {canalSms.envoyer(client.getTelephone(), sujet, sms);
        }
    }
}
