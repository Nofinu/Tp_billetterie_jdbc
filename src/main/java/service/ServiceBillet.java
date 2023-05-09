package service;

import dao.BilletDAO;
import dao.ClientDao;
import dao.EvenementDAO;
import model.Billet;
import model.Client;
import model.Evenement;
import org.example.util.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ServiceBillet {

    private static Scanner scanner = new Scanner(System.in);
    private static Connection connection;
    private static BilletDAO billetDAO;
    private static ClientDao clientDao;
    private static EvenementDAO evenementDAO;

    public static boolean addBilletAction (){
        System.out.println("------- achat d'un billet -------");
        System.out.println("id du client :");
        int idCLient = scanner.nextInt();
        System.out.println("id de l'evenement");
        int idEvent = scanner.nextInt();
        scanner.nextLine();

        try{
            connection = new DatabaseManager().getConnection();
            clientDao = new ClientDao(connection);
            Client client = clientDao.findById(idCLient);
            evenementDAO = new EvenementDAO(connection);
            Evenement event = evenementDAO.findById(idEvent);
            if(client != null && event != null){
                Billet billet = new Billet(client,event);
                billetDAO = new BilletDAO(connection);
                if(billetDAO.save(billet)){
                    System.out.println("billet acheter");
                    event.venteTicket();
                    connection = new DatabaseManager().getConnection();
                    evenementDAO = new EvenementDAO(connection);
                    if(evenementDAO.updateNbrTicket(event)){
                        System.out.println("nbr de billet vendu : "+event.getNbrBilletVendu());
                    }
                }
            }
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean showBilletAction (){
        System.out.println("------ affichage des billets d'un client ----------");
        System.out.println("id du client :");
        int id = scanner.nextInt();
        scanner.nextLine();

        try{
            connection = new DatabaseManager().getConnection();
            billetDAO = new BilletDAO(connection);
            billetDAO.findAllByCLientID(id).forEach(e -> System.out.println(e));
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
