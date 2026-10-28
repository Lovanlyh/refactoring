package fr.efrei.transfret;

import java.util.List;

public class TarificationRail implements StrategieTarification {

    private static final double PRIX_PAR_KM = 0.8;
    private static final double PRIX_PAR_KG = 0.07;
    private static final double PRIX_MINIMUM = 150;

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
