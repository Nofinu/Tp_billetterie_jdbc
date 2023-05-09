package service;

import dao.BilletDAO;
import dao.ClientDao;
import dao.EvenementDAO;
import dao.LieuDAO;
import model.Client;
import model.Evenement;
import model.Lieu;
import org.example.util.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ServiceGeneral {

    private static Connection connection;
    private static LieuDAO lieuDAO;
    private static EvenementDAO evenementDAO;
    private static ClientDao clientDao;
    private static BilletDAO billetDAO;

    private static Scanner scanner = new Scanner(System.in);

    public static boolean addAction (int type){
        try{
            connection = new DatabaseManager().getConnection();
            switch (type){
                case 1 :
                    System.out.println("------- ajout d'un lieu -------");
                    Lieu lieu = ServiceLieu.menuLieu();
                    lieuDAO = new LieuDAO(connection);
                    if (lieuDAO.save(lieu)) {
                        System.out.println("le lieu a ete cree");
                    }
                    break;
                case 4 :
                    System.out.println("------- ajout d'un evenement -------");
                    Evenement evenement = ServiceEvenement.menuEvenement();
                    if(evenement != null){
                        evenementDAO = new EvenementDAO(connection);
                        if(evenementDAO.save(evenement)){
                            System.out.println("l'evenement a ete cree");
                        }
                    }
                    break;
                case 7 :
                    System.out.println("------- ajout d'un lieu -------");
                    Client client = ServiceCLient.menuClient();
                    clientDao = new ClientDao(connection);
                    if(clientDao.save(client)){
                        System.out.println("le client a bien ete cree");
                    }
                    break;
            }
            return true;
        }catch (InputMismatchException m){
            System.out.println("entrer une valeur valide");
            System.out.println(m.getMessage());
            return false;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public static boolean deleteAction (int type){
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
                case 12:
                    System.out.println("----- supresion d'un billet ------");
                    System.out.println("id du billet");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    billetDAO = new BilletDAO(connection);
                    if(billetDAO.delete(id)){
                        System.out.println("billet suprimer");
                    }
                    break;
            }
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean editAction (int type){
        int id;
        try{
            connection = new DatabaseManager().getConnection();
            switch (type){
                case 2 :
                    System.out.println("------- moddification d'un lieu -------");
                    System.out.println("id :");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    Lieu lieu = ServiceLieu.menuLieu();
                    lieu.setId(id);
                    lieuDAO = new LieuDAO(connection);
                    if (lieuDAO.update(lieu)) {
                        System.out.println("le lieu a ete modiffier");
                    }
                    break;
                case 5 :
                    System.out.println("------- moddification d'un evenement -------");
                    System.out.println("id :");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    Evenement evenement = ServiceEvenement.menuEvenement();
                    evenement.setId(id);
                    if(evenement != null){
                        evenementDAO = new EvenementDAO(connection);
                        if(evenementDAO.update(evenement)){
                            System.out.println("l'evenement a ete modiffier");
                        }
                    }
                    break;
                case 8 :
                    System.out.println("------- moddification d'un client -------");
                    System.out.println("id :");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    Client client = ServiceCLient.menuClient();
                    client.setId(id);
                    clientDao = new ClientDao(connection);
                    if(clientDao.update(client)){
                        System.out.println("le client a bien ete modiffier");
                    }
                    break;
            }
            return true;
        }catch (InputMismatchException m){
            System.out.println("entrer une valeur valide");
            System.out.println(m.getMessage());
            return false;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void showAllEventAction (){
        try{
            connection = new DatabaseManager().getConnection();
            evenementDAO = new EvenementDAO(connection);
            evenementDAO.findAll().forEach(e -> System.out.println(e));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
