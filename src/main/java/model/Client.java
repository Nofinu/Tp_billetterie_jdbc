package model;

import java.util.List;

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private List<Evenement> billetsList;

    public Client(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Client(int id, String nom, String prenom, String email, List<Evenement> billetsList) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.billetsList = billetsList;
    }

    public Client(int id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Evenement> getBilletsList() {
        return billetsList;
    }

    public void setBilletsList(List<Evenement> billetsList) {
        this.billetsList = billetsList;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", billetsList=" + billetsList +
                '}';
    }
}
