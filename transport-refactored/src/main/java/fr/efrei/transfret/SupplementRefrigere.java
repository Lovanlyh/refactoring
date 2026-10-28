
package fr.efrei.transfret;

public class SupplementRefrigere extends DecorateurPrix {

    private static final double MAJORATION = 1.20;

    public SupplementRefrigere(CalculPrix calculPrix) {
        super(calculPrix);
    }

    @Override
    public double calculer() {
        return calculPrix.calculer() * MAJORATION;
    }
}
