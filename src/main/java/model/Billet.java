package model;

import org.example.util.TypeBillet;

public class Billet {
    private int id;
    private Client client;
    private Evenement evenement;

    private TypeBillet typeBillet;

    public Billet(Client client, Evenement evenement,TypeBillet typeBillet) {
        this.client = client;
        this.evenement = evenement;
        this.typeBillet = typeBillet;
    }

    public Billet(int id, Client client, Evenement evenement,TypeBillet typeBillet) {
        this(client, evenement, typeBillet);
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

    public TypeBillet getTypeBillet() {
        return typeBillet;
    }

    @Override
    public String toString() {
        return "Billet{" +
                "id=" + id +
                "\n client=" + client +
                "\n evenement=" + evenement +
                "\n type billet = "+typeBillet.toString()+
                "}\n\n";
    }
}