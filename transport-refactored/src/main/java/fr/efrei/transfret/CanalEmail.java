package fr.efrei.transfret;

public class CanalEmail implements CanalNotification {

    @Override
    public void envoyer(
            String destinataire,
            String sujet,
            String message) {

        EmailService.envoyer(destinataire, sujet, message);
    }
}
