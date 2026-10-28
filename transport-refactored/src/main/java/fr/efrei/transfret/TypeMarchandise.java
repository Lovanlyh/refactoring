package fr.efrei.transfret;

public enum TypeMarchandise {
    STANDARD(0, "Standard"),
    FRAGILE(1, "Fragile"),
    DANGEREUX(2, "Dangereux"),
    REFRIGERE(3, "Réfrigéré");

    private final int code;
    private final String libelle;

    TypeMarchandise(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public int getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public static TypeMarchandise depuisCode(int code) {
        for (TypeMarchandise type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException(
                "Type de marchandise inconnu : " + code
        );
    }
}
