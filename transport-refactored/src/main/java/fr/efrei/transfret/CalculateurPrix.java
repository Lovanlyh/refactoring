
package fr.efrei.transfret;

import java.util.List;

public class CalculateurPrix {

    private static final double ROUTE_PAR_KM = 1.2;
    private static final double ROUTE_PAR_KG = 0.10;
    private static final double RAIL_PAR_KM = 0.8;
    private static final double RAIL_PAR_KG = 0.07;
    private static final double RAIL_MINIMUM = 150;
    private static final double MER_PAR_KM = 0.5;
    private static final double MER_PAR_KG = 0.03;
    private static final double MER_MINIMUM = 500;
    private static final double AIR_PAR_KM = 0.6;
    private static final double AIR_PAR_KG = 4.5;
    private static final double AIR_POIDS_VOLUMETRIQUE = 167;

    private static final double MAJORATION_FRAGILE = 1.15;
    private static final double MAJORATION_DANGEREUX = 1.30;
    private static final double MAJORATION_REFRIGERE = 1.20;
    private static final double MAJORATION_URGENT = 1.50;

    private static final double SEUIL_ENTREPRISE = 5000;
    private static final double REMISE_ENTREPRISE = 0.90;
    private static final double REMISE_ENTREPRISE_IMPORTANTE = 0.85;
    private static final double REMISE_PREMIUM = 0.80;

    private static final double TAUX_ASSURANCE = 0.02;
    private static final double ASSURANCE_MINIMUM = 15;

    public double calculerPrixHT(
            List<Colis> colis,
            int distance,
            String mode,
            boolean urgent,
            boolean assurance,
            String typeClient) {

        ModeTransport modeTransport = ModeTransport.valueOf(mode);
        TypeClient categorieClient = TypeClient.valueOf(typeClient);

        double poids = 0;
        double volume = 0;
        double valeur = 0;

        boolean fragile = false;
        boolean dangereux = false;
        boolean refrigere = false;

        for (Colis c : colis) {
            poids += c.getPoids();
            volume += c.getVolume();
            valeur += c.getValeurDeclaree();

            TypeMarchandise type = c.getTypeMarchandise();

            if (type == TypeMarchandise.FRAGILE) fragile = true;
            if (type == TypeMarchandise.DANGEREUX) dangereux = true;
            if (type == TypeMarchandise.REFRIGERE) refrigere = true;
        }

        double prix;

        switch (modeTransport) {
            case ROUTE -> prix =
                    distance * ROUTE_PAR_KM + poids * ROUTE_PAR_KG;

            case RAIL -> prix = Math.max(
                    RAIL_MINIMUM,
                    distance * RAIL_PAR_KM + poids * RAIL_PAR_KG
            );

            case MER -> prix = Math.max(
                    MER_MINIMUM,
                    distance * MER_PAR_KM + poids * MER_PAR_KG
            );

            case AIR -> {
                double poidsTaxable = Math.max(
                        poids,
                        volume * AIR_POIDS_VOLUMETRIQUE
                );
                prix = distance * AIR_PAR_KM
                        + poidsTaxable * AIR_PAR_KG;
            }

            default -> throw new IllegalArgumentException(
                    "Mode inconnu : " + mode
            );
        }

        if (fragile) prix *= MAJORATION_FRAGILE;
        if (dangereux) prix *= MAJORATION_DANGEREUX;
        if (refrigere) prix *= MAJORATION_REFRIGERE;
        if (urgent) prix *= MAJORATION_URGENT;

        if (categorieClient == TypeClient.ENTREPRISE) {
            prix *= prix > SEUIL_ENTREPRISE
                    ? REMISE_ENTREPRISE_IMPORTANTE
                    : REMISE_ENTREPRISE;
        } else if (categorieClient == TypeClient.PREMIUM) {
            prix *= REMISE_PREMIUM;
        }

        if (assurance) {
            double montantAssurance = Math.max(
                    ASSURANCE_MINIMUM,
                    valeur * TAUX_ASSURANCE
            );
            prix += montantAssurance;
        }

        return Utils.arrondir(prix);
    }
}
