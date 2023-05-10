package service;

import dao.BilletDAO;
import dao.ClientDao;
import dao.EvenementDAO;
import model.Billet;
import model.Client;
import model.Evenement;
import org.example.util.DatabaseManager;
import org.example.util.TypeBillet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServiceBillet {

    private static Connection connection;
    private static BilletDAO billetDAO;
    private static ClientDao clientDao;
    private static EvenementDAO evenementDAO;

    public ServiceBillet(){
        try{
            connection = new DatabaseManager().getConnection();
            evenementDAO = new EvenementDAO(connection);
            billetDAO = new BilletDAO(connection);
            clientDao = new ClientDao(connection);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean addBilletAction (int idClient, int idEvent, TypeBillet typeBillet){
        try{
            connection = new DatabaseManager().getConnection();
            clientDao = new ClientDao(connection);
            Client client = clientDao.findById(idClient);
            evenementDAO = new EvenementDAO(connection);
            Evenement event = evenementDAO.findById(idEvent);
            if(client != null && event != null){
                Billet billet = new Billet(client,event,typeBillet);
                billetDAO = new BilletDAO(connection);
                if(billetDAO.save(billet)){
                    event.venteTicket();
                    connection = new DatabaseManager().getConnection();
                    evenementDAO = new EvenementDAO(connection);
                    if(evenementDAO.updateNbrTicket(event)){
                        return true;
                    }
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean deleteBillet (int id){
        Billet billet = null;
        try{
            billet = billetDAO.findById(id);
            if(billet != null){
                return billetDAO.delete(billet);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Billet> showBilletAction (int id){
        List<Billet> billets = null;
        try{
            connection = new DatabaseManager().getConnection();
            billetDAO = new BilletDAO(connection);
            billets = billetDAO.findAllByCLientID(id);
            return billets;
        }catch (SQLException e){
            System.out.println(e.getMessage());

        }
        return null;
    }
}
