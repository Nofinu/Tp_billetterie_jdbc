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

    public Evenement(int id, int nbrBilletVendu) {
        this.id = id;
        this.nbrBilletVendu = nbrBilletVendu;
    }

    public String getNom() {
        return nom;
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

    public String getHeure() {
        return heure;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public float getPrix() {
        return prix;
    }

    public int getNbrBilletVendu() {
        return nbrBilletVendu;
    }

    public void venteTicket (){
        this.nbrBilletVendu ++;
    }

    public void annulationTicket (){
        this.nbrBilletVendu --;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", date=" + date +
                ", heure='" + heure + '\'' +
                ", lieu=" + lieu +
                ", prix=" + prix +
                ", nbrBilletVendu=" + nbrBilletVendu +
                '}';
    }
}
