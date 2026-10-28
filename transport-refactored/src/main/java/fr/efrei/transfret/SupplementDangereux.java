package fr.efrei.transfret;

public class SupplementDangereux extends DecorateurPrix {

    private static final double MAJORATION = 1.30;

    public SupplementDangereux(CalculPrix calculPrix) {
        super(calculPrix);
    }

    @Override
    public double calculer() {
        return calculPrix.calculer() * MAJORATION;
    }
}
