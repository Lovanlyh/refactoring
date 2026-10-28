package fr.efrei.transfret;

import java.util.List;

public interface StrategieTarification {

    double calculerTarifBase(List<Colis> colis, int distance);
}
