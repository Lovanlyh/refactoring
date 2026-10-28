package fr.efrei.transfret;

public class SupplementUrgent extends DecorateurPrix {

    private static final double MAJORATION = 1.50;

    public SupplementUrgent(CalculPrix calculPrix) {
        super(calculPrix);
    }

    @Override
    public double calculer() {
        return calculPrix.calculer() * MAJORATION;
    }
}
