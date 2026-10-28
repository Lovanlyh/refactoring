package fr.efrei.transfret;

public interface ExpeditionRepository {

    void sauvegarderExpedition(Expedition expedition);

    Expedition trouverExpedition(String id);

    void mettreAJourStatut(Expedition expedition);
}
