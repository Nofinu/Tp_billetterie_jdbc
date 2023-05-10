package service;

import dao.LieuDAO;
import model.Lieu;
import org.example.util.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ServiceLieu {
    private Connection connection;
    private LieuDAO lieuDAO;

    public ServiceLieu(){
        try{
            connection = new DatabaseManager().getConnection();
            lieuDAO = new LieuDAO(connection);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean addLieu (String nom,String adresse,int capacite){
        Lieu lieu = new Lieu(nom,adresse,capacite);
        try{
            if (lieuDAO.save(lieu)){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean deleteLieu (int id){
        Lieu lieu = null;
        try{
            lieu = lieuDAO.findById(id);
            if(lieu != null){
                return lieuDAO.delete(lieu);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean editLieu (Lieu lieu){
        try{
            if(lieuDAO.update(lieu)){
                return true;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Lieu> findAllLieu (){
        try{
            return lieuDAO.findAll();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Lieu findByIdLieu (int id){
        try{
            return lieuDAO.findById(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
