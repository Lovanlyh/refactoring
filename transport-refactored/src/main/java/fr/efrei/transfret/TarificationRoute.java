package fr.efrei.transfret;

import java.util.List;

public class TarificationRoute implements StrategieTarification {

    private static final double PRIX_PAR_KM = 1.2;
    private static final double PRIX_PAR_KG = 0.10;

    @Override
    public double calculerTarifBase(List<Colis> colis, int distance) {

        double poids = 0;

        for (Colis c : colis) {
            poids += c.getPoids();
        }

        return distance * PRIX_PAR_KM
                + poids * PRIX_PAR_KG;
    }
}
