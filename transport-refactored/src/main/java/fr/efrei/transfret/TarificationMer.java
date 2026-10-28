package fr.efrei.transfret;

import java.util.List;

public class TarificationMer implements StrategieTarification {

    private static final double PRIX_PAR_KM = 0.5;
    private static final double PRIX_PAR_KG = 0.03;
    private static final double PRIX_MINIMUM = 500;

    @Override
    public double calculerTarifBase(List<Colis> colis, int distance) {

        double poids = 0;

        for (Colis c : colis) {
            poids += c.getPoids();
        }

        double prix = distance * PRIX_PAR_KM
                + poids * PRIX_PAR_KG;

        return Math.max(PRIX_MINIMUM, prix);
    }
}
