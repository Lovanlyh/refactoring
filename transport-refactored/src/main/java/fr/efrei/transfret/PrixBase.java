package fr.efrei.transfret;

import java.util.List;

public class PrixBase implements CalculPrix {

    private final StrategieTarification strategie;
    private final List<Colis> colis;
    private final int distance;

    public PrixBase(
            StrategieTarification strategie,
            List<Colis> colis,
            int distance) {

        this.strategie = strategie;
        this.colis = colis;
        this.distance = distance;
    }

    @Override
    public double calculer() {
        return strategie.calculerTarifBase(colis, distance);
    }
}
