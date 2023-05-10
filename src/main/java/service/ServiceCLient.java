package service;

import dao.ClientDao;
import dao.LieuDAO;
import model.Client;
import model.Lieu;
import org.example.util.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ServiceCLient {
    private Connection connection;
    private ClientDao clientDao;

    public ServiceCLient (){
        try{
            connection = new DatabaseManager().getConnection();
            clientDao = new ClientDao(connection);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public boolean addClient (String nom,String prenom,String email){
        Client client = new Client(nom,prenom,email);
        try{
            if (clientDao.save(client)){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean deleteClient (int id){
        Client client = null;
        try{
            client = clientDao.findById(id);
            if(client != null){
                return clientDao.delete(client);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean editCLient (Client client){
        try{
            if(clientDao.update(client)){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Client> findAllClient (){
        try{
            return clientDao.findAll();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Client findByIdCLient(int id){
        try{
            return clientDao.findById(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
