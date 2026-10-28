package fr.efrei.transfret;

public class SupplementFragile extends DecorateurPrix {

    private static final double MAJORATION = 1.15;

    public SupplementFragile(CalculPrix calculPrix) {
        super(calculPrix);
    }

    @Override
    public double calculer() {
        return calculPrix.calculer() * MAJORATION;
    }
}
