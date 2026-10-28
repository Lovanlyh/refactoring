package fr.efrei.transfret;

public abstract class DecorateurPrix implements CalculPrix {

    protected final CalculPrix calculPrix;

    protected DecorateurPrix(CalculPrix calculPrix) {
        this.calculPrix = calculPrix;
    }
}
