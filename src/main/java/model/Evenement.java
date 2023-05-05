package model;

import java.util.Date;

public class Evenement {
    private int id;
    private String nom;
    private Date date;
    private String heure;
    private Lieu lieu ;
    private float prix;
    private int nbrBilletVendu = 0;

    public Evenement(String nom,Date date, String heure, Lieu lieu, float prix) {
        this.nom = nom;
        this.date = date;
        this.heure = heure;
        this.lieu = lieu;
        this.prix = prix;
    }

    public Evenement(int id,String nom, Date date, String heure, Lieu lieu, float prix, int nbrBilletVendu) {
        this(nom,date,heure,lieu,prix);
        this.id = id;
        this.nbrBilletVendu = nbrBilletVendu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbrBilletVendu() {
        return nbrBilletVendu;
    }

    public void setNbrBilletVendu(int nbrBilletVendu) {
        this.nbrBilletVendu = nbrBilletVendu;
    }
}
