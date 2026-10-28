
package fr.efrei.transfret;

import java.util.List;

public class AffectationVehicule {

    public Vehicule trouverVehicule(List<Colis> colis, String mode) {
        double poids = 0;
        boolean dangereux = false;
        boolean refrigere = false;

        for (Colis c : colis) {
            poids += c.getPoids();
            if (c.getTypeMarchandise() == TypeMarchandise.DANGEREUX) {
                dangereux = true;
            }
            if (c.getTypeMarchandise() == TypeMarchandise.REFRIGERE) {
                refrigere = true;
            }
        }

        ModeTransport modeTransport;
        try {
            modeTransport = ModeTransport.valueOf(mode);
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }

        for (Vehicule v : Flotte.vehicules) {
            if (!v.estDisponible()) {
                continue;
            }
            if (v.getCapaciteMax() - v.getChargeActuelle() < poids) {
                continue;
            }

            if (modeTransport == ModeTransport.ROUTE) {
                if (v instanceof Camion) {
                    if (refrigere) {
                        if (v instanceof CamionFrigorifique) {
                            return v;
                        }
                    } else if (v instanceof CamionFrigorifique) {
                        continue;
                    } else if (dangereux) {
                        if (v.peutTransporterMatieresDangereuses()) {
                            return v;
                        }
                    } else {
                        return v;
                    }
                }

            } else if (modeTransport == ModeTransport.RAIL) {
                if (v instanceof Train) {
                    return v;
                }

            } else if (modeTransport == ModeTransport.MER) {
                if (v instanceof Bateau) {
                    if (refrigere
                            && !((Bateau) v).aConteneursRefrigeres()) {
                        continue;
                    }
                    return v;
                }

            } else if (modeTransport == ModeTransport.AIR) {
                if (v instanceof Avion) {
                    return v;
                }
            }
        }

        return null;
    }
}
