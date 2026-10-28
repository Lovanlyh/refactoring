package fr.efrei.transfret;

import java.util.Date;

public class Client {

    private String nom;
    private String email;
    private String telephone;
    private String adresse;

    private TypeClient type;

    private String siret;

    private int nbExpeditions;
    private Date dateInscription;

    public Client() {
    }

    public Client(String nom, String email, String telephone,
                  String adresse, String type, String siret) {

        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;

        this.type = type == null ? null : TypeClient.valueOf(type);

        this.siret = siret;
        this.nbExpeditions = 0;
        this.dateInscription = new Date();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type == null ? null : type.name();
    }

    public TypeClient getTypeClient() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : TypeClient.valueOf(type);
    }

    public void setTypeClient(TypeClient type) {
        this.type = type;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public int getNbExpeditions() {
        return nbExpeditions;
    }

    public void setNbExpeditions(int nbExpeditions) {
        this.nbExpeditions = nbExpeditions;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    @Override
    public String toString() {
        return nom + " (" + getType() + ")";
    }
}
