
package fr.efrei.transfret;

import java.util.Date;

public class GenerateurFacture {

    public String generer(Expedition e) {
        StringBuilder sb = new StringBuilder();

        sb.append("==============================================\n");
        sb.append("              FACTURE TRANSFRET\n");
        sb.append("==============================================\n");

        sb.append("Facture n° F-" + e.getId() + "\n");
        sb.append("Date : " + Utils.formaterDate(new Date()) + "\n");
        sb.append("Client : " + e.getClient().getNom() + "\n");

        if (e.getClient().getTypeClient() == TypeClient.ENTREPRISE) {
            sb.append("SIRET : " + e.getClient().getSiret() + "\n");
        }

        sb.append("Adresse : " + e.getClient().getAdresse() + "\n");
        sb.append("----------------------------------------------\n");
        sb.append("Trajet : " + e.getVilleDepart() + " -> " + e.getVilleArrivee() + " (" + e.getDistance() + " km)\n");
        sb.append("Mode : " + e.getMode() + (e.isUrgent() ? " (URGENT)" : "") + "\n");
        sb.append("Colis :\n");

        for (Colis c : e.getColis()) {
            sb.append("  - " + c.getDescription() + " : " + c.getPoids() + " kg [" + c.getLibelleType() + "]\n");
        }

        sb.append("----------------------------------------------\n");

        if (e.getStatutExpedition() == StatutExpedition.ANNULEE) {
            sb.append("EXPEDITION ANNULEE\n");
            sb.append("Frais d'annulation TTC : " + String.format("%.2f", e.getFraisAnnulation()) + " €\n");
        } else {
            sb.append("Total HT  : " + String.format("%.2f", e.getPrixHT()) + " €\n");
            sb.append("TVA 20%   : " + String.format("%.2f", e.getPrixTTC() - e.getPrixHT()) + " €\n");
            sb.append("Total TTC : " + String.format("%.2f", e.getPrixTTC()) + " €\n");
        }

        sb.append("==============================================\n");

        return sb.toString();
    }
}
