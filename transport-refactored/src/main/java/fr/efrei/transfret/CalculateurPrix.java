package fr.efrei.transfret;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CalculateurPrix {

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

    private final Map<ModeTransport, StrategieTarification> strategies;

    public CalculateurPrix() {

        strategies = new EnumMap<>(ModeTransport.class);

        strategies.put(ModeTransport.ROUTE, new TarificationRoute());

        strategies.put(ModeTransport.RAIL, new TarificationRail());

        strategies.put(ModeTransport.MER, new TarificationMer());

        strategies.put(ModeTransport.AIR, new TarificationAir());
    }

    public double calculerPrixHT(List<Colis> colis, int distance, String mode, boolean urgent, boolean assurance, String typeClient) {

        ModeTransport modeTransport = ModeTransport.valueOf(mode);
        TypeClient categorieClient = TypeClient.valueOf(typeClient);

        double valeur = 0;

        boolean fragile = false;
        boolean dangereux = false;
        boolean refrigere = false;

        for (Colis c : colis) {

            valeur += c.getValeurDeclaree();

            TypeMarchandise type = c.getTypeMarchandise();

            if (type == TypeMarchandise.FRAGILE) {
                fragile = true;
            }

            if (type == TypeMarchandise.DANGEREUX) {
                dangereux = true;
            }

            if (type == TypeMarchandise.REFRIGERE) {
                refrigere = true;
            }
        }

        StrategieTarification strategie = strategies.get(modeTransport);

        if (strategie == null) {
            throw new IllegalArgumentException(
                    "Mode inconnu : " + mode
            );
        }

        double prix = strategie.calculerTarifBase(
                colis,
                distance
        );

        if (fragile) {
            prix *= MAJORATION_FRAGILE;
        }

        if (dangereux) {
            prix *= MAJORATION_DANGEREUX;
        }

        if (refrigere) {
            prix *= MAJORATION_REFRIGERE;
        }

        if (urgent) {
            prix *= MAJORATION_URGENT;
        }

        if (categorieClient == TypeClient.ENTREPRISE) {

            prix *= prix > SEUIL_ENTREPRISE
                    ? REMISE_ENTREPRISE_IMPORTANTE
                    : REMISE_ENTREPRISE;

        } else if (categorieClient == TypeClient.PREMIUM) {

            prix *= REMISE_PREMIUM;
        }

        if (assurance) {

            double montantAssurance = Math.max(ASSURANCE_MINIMUM, valeur * TAUX_ASSURANCE);

            prix += montantAssurance;
        }

        return Utils.arrondir(prix);
    }
}
