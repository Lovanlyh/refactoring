
package fr.efrei.transfret;

public class EstimateurDelai {

    private static final double VITESSE_ROUTE_KMH = 70.0;
    private static final double HEURES_ROUTE_NORMAL = 9.0;
    private static final double HEURES_ROUTE_URGENT = 18.0;

    private static final double DISTANCE_RAIL_PAR_JOUR = 1440.0;
    private static final int MARGE_RAIL_JOURS = 1;

    private static final double DISTANCE_MER_PAR_JOUR = 720.0;
    private static final int MARGE_MER_JOURS = 2;

    private static final int SEUIL_AIR_LONGUE_DISTANCE_KM = 3000;
    private static final int DELAI_AIR_COURT = 1;
    private static final int DELAI_AIR_LONG = 2;

    private static final int DELAI_MINIMUM_JOURS = 1;

    public int estimer(int distance, ModeTransport mode, boolean urgent) {
        int jours = 0;

        if (mode == null) {
            return DELAI_MINIMUM_JOURS;
        }

        switch (mode) {
            case ROUTE -> {
                double heures = distance / VITESSE_ROUTE_KMH;
                double heuresParJour = urgent
                        ? HEURES_ROUTE_URGENT
                        : HEURES_ROUTE_NORMAL;

                jours = (int) Math.ceil(heures / heuresParJour);
            }

            case RAIL -> {
                jours = (int) Math.ceil(
                        distance / DISTANCE_RAIL_PAR_JOUR
                ) + MARGE_RAIL_JOURS;

                if (urgent) {
                    jours--;
                }
            }

            case MER -> jours = (int) Math.ceil(
                    distance / DISTANCE_MER_PAR_JOUR
            ) + MARGE_MER_JOURS;

            case AIR -> {
                jours = distance > SEUIL_AIR_LONGUE_DISTANCE_KM
                        ? DELAI_AIR_LONG
                        : DELAI_AIR_COURT;

                if (urgent) {
                    jours = DELAI_AIR_COURT;
                }
            }
        }

        return Math.max(jours, DELAI_MINIMUM_JOURS);
    }
}
