package fr.efrei.transfret;

import java.util.List;

public class TarificationAir implements StrategieTarification {

    private static final double PRIX_PAR_KM = 0.6;
    private static final double PRIX_PAR_KG = 4.5;
    private static final double COEFFICIENT_POIDS_VOLUMETRIQUE = 167;

    @Override
    public double calculerTarifBase(List<Colis> colis, int distance) {

        double poids = 0;
        double volume = 0;

        for (Colis c : colis) {
            poids += c.getPoids();
            volume += c.getVolume();
        }

        double poidsTaxable = Math.max(
                poids,
                volume * COEFFICIENT_POIDS_VOLUMETRIQUE
        );

        return distance * PRIX_PAR_KM
                + poidsTaxable * PRIX_PAR_KG;
    }
}
