package fr.efrei.transfret;

public class SupplementAssurance extends DecorateurPrix {

    private static final double TAUX_ASSURANCE = 0.02;
    private static final double ASSURANCE_MINIMUM = 15.0;

    private final double valeurDeclaree;

    public SupplementAssurance(
            CalculPrix calculPrix,
            double valeurDeclaree) {

        super(calculPrix);
        this.valeurDeclaree = valeurDeclaree;
    }

    @Override
    public double calculer() {

        double montantAssurance = Math.max(
                ASSURANCE_MINIMUM,
                valeurDeclaree * TAUX_ASSURANCE
        );

        return calculPrix.calculer() + montantAssurance;
    }
}
