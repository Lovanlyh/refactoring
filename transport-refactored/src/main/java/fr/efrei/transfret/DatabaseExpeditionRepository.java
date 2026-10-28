package fr.efrei.transfret;

public class DatabaseExpeditionRepository
        implements ExpeditionRepository {

    private final Database database;

    public DatabaseExpeditionRepository() {
        this.database = Database.getInstance();
    }

    @Override
    public void sauvegarderExpedition(Expedition expedition) {
        database.sauvegarderExpedition(expedition);
    }

    @Override
    public Expedition trouverExpedition(String id) {
        return database.trouverExpedition(id);
    }

    @Override
    public void mettreAJourStatut(Expedition expedition) {
        database.mettreAJourStatut(expedition);
    }
}
