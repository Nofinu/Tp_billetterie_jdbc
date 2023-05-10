package service;

import dao.EvenementDAO;
import dao.LieuDAO;
import model.Evenement;
import model.Lieu;
import org.example.util.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class ServiceEvenement {

    private Connection connection;
    private EvenementDAO evenementDAO;

    public ServiceEvenement(){
        try{
            connection = new DatabaseManager().getConnection();
            evenementDAO = new EvenementDAO(connection);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean addEvent (String nom,Date date,String heure,int idlieu,float prix){
        try{
            Connection connectionLieu = new DatabaseManager().getConnection();
            LieuDAO LieuDaoEvenement = new LieuDAO(connectionLieu);
            Lieu lieu = LieuDaoEvenement.findById(idlieu);

            if(lieu != null){
                Evenement evenement = new Evenement(nom,date,heure,lieu,prix);
                if (evenementDAO.save(evenement)){
                    return true;
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }


    public boolean deleteEvenement(int id){
        Evenement evenement = null;
        try{
            evenement = evenementDAO.findById(id);
            if(evenement != null){
                return evenementDAO.delete(evenement);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean editEvenement (int id,String nom,Date date,String heure,int idlieu,float prix){
        try{
            Connection connectionLieu = new DatabaseManager().getConnection();
            LieuDAO LieuDaoEvenement = new LieuDAO(connectionLieu);
            Lieu lieu = LieuDaoEvenement.findById(idlieu);
            Evenement eventfind = evenementDAO.findById(id);
            if(lieu != null){
                Evenement evenement = new Evenement(id,nom,date,heure,lieu,prix,eventfind.getNbrBilletVendu());
                if(evenementDAO.update(evenement)){
                    return true;
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Evenement> findAllEvenement (){
        try{
            return evenementDAO.findAll();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Evenement findByIdEvenement (int id){
        try{
            return evenementDAO.findById(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
