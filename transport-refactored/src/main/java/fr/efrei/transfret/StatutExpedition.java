package fr.efrei.transfret;

public enum StatutExpedition {
    CREEE("Créée"),
    EN_COURS("En cours"),
    LIVREE("Livrée"),
    ANNULEE("Annulée");

    private final String libelle;

    StatutExpedition(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
