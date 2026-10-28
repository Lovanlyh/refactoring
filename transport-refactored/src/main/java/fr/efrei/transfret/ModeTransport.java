package fr.efrei.transfret;

public enum ModeTransport {
    ROUTE("Routier"),
    RAIL("Ferroviaire"),
    MER("Maritime"),
    AIR("Aérien");

    private final String libelle;

    ModeTransport(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
