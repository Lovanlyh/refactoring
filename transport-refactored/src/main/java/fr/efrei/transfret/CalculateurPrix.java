
package fr.efrei.transfret;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class CalculateurPrix {

    private static final double SEUIL_ENTREPRISE = 5000;
    private static final double REMISE_ENTREPRISE = 0.90;
    private static final double REMISE_ENTREPRISE_IMPORTANTE = 0.85;
    private static final double REMISE_PREMIUM = 0.80;

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

        boolean fragile = false;
        boolean dangereux = false;
        boolean refrigere = false;
        double valeurDeclaree = 0;

        for (Colis colisCourant : colis) {

            valeurDeclaree += colisCourant.getValeurDeclaree();

            TypeMarchandise type = colisCourant.getTypeMarchandise();

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

        StrategieTarification strategie =
                strategies.get(modeTransport);

        if (strategie == null) {
            throw new IllegalArgumentException(
                    "Mode inconnu : " + mode
            );
        }

        CalculPrix calcul = new PrixBase(strategie, colis, distance);

        if (fragile) {
            calcul = new SupplementFragile(calcul);
        }

        if (dangereux) {
            calcul = new SupplementDangereux(calcul);
        }

        if (refrigere) {
            calcul = new SupplementRefrigere(calcul);
        }

        if (urgent) {
            calcul = new SupplementUrgent(calcul);
        }

        double prix = calcul.calculer();

        if (categorieClient == TypeClient.ENTREPRISE) {

            prix *= prix > SEUIL_ENTREPRISE ? REMISE_ENTREPRISE_IMPORTANTE : REMISE_ENTREPRISE;

        } else if (categorieClient == TypeClient.PREMIUM) {

            prix *= REMISE_PREMIUM;
        }

        if (assurance) {

            final double prixApresRemise = prix;
            final double valeurTotaleDeclaree = valeurDeclaree;

            CalculPrix prixAvecAssurance =
                    new SupplementAssurance(() -> prixApresRemise, valeurTotaleDeclaree);

            prix = prixAvecAssurance.calculer();
        }

        return Utils.arrondir(prix);
    }
}