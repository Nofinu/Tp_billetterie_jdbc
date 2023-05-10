package model;

public class Billet {
    private int id;
    private Client client;
    private Evenement evenement;

    public Billet(Client client, Evenement evenement) {
        this.client = client;
        this.evenement = evenement;
    }

    public Billet(int id, Client client, Evenement evenement) {
        this(client, evenement);
        this.id = id;
    }

    public Billet(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Billet{" +
                "id=" + id +
                "\n client=" + client +
                "\n evenement=" + evenement +
                "}\n\n";
    }
}