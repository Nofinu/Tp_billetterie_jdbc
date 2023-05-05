package model;

import dao.ClientDao;
import dao.EvenementDAO;
import dao.LieuDAO;
import jdk.jshell.spi.ExecutionControl;
import model.Lieu;
import org.example.util.DatabaseManager;

import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IHM {

    private Scanner scanner;
    private Connection connection;

    private LieuDAO lieuDAO;
    private EvenementDAO evenementDAO;
    private ClientDao clientDao;

    public IHM() {
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        int entry;
        do{
            menu();
            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry){
                case 1 :
                case 4 :
                case 7 :
                    addAction(entry);
                    break;
                case 2 :
                case 5 :
                case 8 :
                    System.out.println("modifier");
                    break;
                case 3 :
                case 6 :
                case 9 :
                    deleteAction(entry);
                    break;

            }

        }while(entry !=0);

    }

    private void menu(){
        System.out.println("----- menu -----");
        System.out.println("1-- ajouter un lieu");
        System.out.println("2-- modifier un lieu");
        System.out.println("3-- supprimer un lieu");
        System.out.println("------------");
        System.out.println("4-- ajouter un evenement");
        System.out.println("5-- modifier un evenement");
        System.out.println("6-- supprimer un evenement");
        System.out.println("------------");
        System.out.println("7-- ajouter un client");
        System.out.println("8-- modifier un client");
        System.out.println("9-- supprimer un client");
        System.out.println("------------");
    }

    //action pour ajouter des composant a notre base
    private void addAction (int type){
        try{
            connection = new DatabaseManager().getConnection();
            switch (type){
                case 1 :
                    Lieu lieu = menuAddLieu();
                    lieuDAO = new LieuDAO(connection);
                    if (lieuDAO.save(lieu)) {
                        System.out.println("le lieu a ete cree");
                    }
                    break;
                case 4 :
                    Evenement evenement =  menuAddEvenement();
                    if(evenement != null){
                        evenementDAO = new EvenementDAO(connection);
                        if(evenementDAO.save(evenement)){
                            System.out.println("l'evenement a ete cree");
                        }
                    }
                    break;
                case 7 :
                    Client client = menuAddClient();
                    clientDao = new ClientDao(connection);
                    if(clientDao.save(client)){
                        System.out.println("le client a bien ete cree");
                    }
                    break;
            }
        }catch (InputMismatchException m){
            System.out.println("entrer une valeur valide");
            System.out.println(m.getMessage());
            addAction(type);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    private Lieu menuAddLieu (){
        System.out.println("------- ajout d'un lieu -------");
        System.out.println("nom :");
        String nom = scanner.nextLine();
        System.out.println("adresse :");
        String adresse = scanner.nextLine();
        System.out.println("capacité :");
        int capacite = scanner.nextInt();
        scanner.nextLine();

        Lieu lieu = new Lieu(nom,adresse,capacite);
        return lieu;
    }

    private Evenement menuAddEvenement (){
        System.out.println("------- ajout d'un evenement -------");
        System.out.println("nom :");
        String nom = scanner.nextLine();
        System.out.println("date format(dd-MM-yyyy) :");
        String dateString = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            date = new Date("01/01/2001");
        }

        System.out.println("heure :");
        String heure = scanner.nextLine();

        System.out.println("lieu (id) :");
        int idLieu = scanner.nextInt();
        scanner.nextLine();
        try{
            Connection connectionLieu = new DatabaseManager().getConnection();
            LieuDAO LieuDaoEvenement = new LieuDAO(connectionLieu);
            Lieu lieu = LieuDaoEvenement.findById(idLieu);
            if(lieu == null){
                System.out.println("aucun lieu trouver a cette id ");
                return null;
            }
            System.out.println("prix :");
            float prix = scanner.nextFloat();
            scanner.nextLine();
            Evenement evenement = new Evenement(nom,date,heure,lieu,prix);
            return evenement;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Client menuAddClient (){
        System.out.println("------- ajout d'un lieu -------");
        System.out.println("nom :");
        String nom = scanner.nextLine();
        System.out.println("prenom :");
        String prenom = scanner.nextLine();
        System.out.println("email :");
        String email = scanner.nextLine();

        Client client = new Client(nom,prenom,email);
        return client;
    }

    //action pour modifier les composant de notre base

    //action pour suprimer les composant de notre base
    private void deleteAction (int type){
        try{
            int id;
            connection = new DatabaseManager().getConnection();
            switch (type){
                case 3:
                    System.out.println("----- supresion d'un lieu ------");
                    System.out.println("id du lieu :");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    lieuDAO = new LieuDAO(connection);
                    if(lieuDAO.delete(id)){
                        System.out.println("lieu suprimer");
                    }
                    break;
                case 6:
                    System.out.println("----- supresion d'un evenement ------");
                    System.out.println("id de l'evenement");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    evenementDAO = new EvenementDAO(connection);
                    if(evenementDAO.delete(id)){
                        System.out.println("evenement suprimer");
                    }
                    break;
                case 9:
                    System.out.println("----- supresion d'un client ------");
                    System.out.println("id du client");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    clientDao = new ClientDao(connection);
                    if(clientDao.delete(id)){
                        System.out.println("client suprimer");
                    }
                    break;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}


