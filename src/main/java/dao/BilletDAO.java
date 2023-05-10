package dao;

import jdk.jshell.spi.ExecutionControl;
import model.Billet;
import model.Client;
import model.Evenement;
import org.example.util.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BilletDAO extends BaseDAO<Billet> {

    public BilletDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Billet element) throws SQLException {
        request = "INSERT INTO billet(id_client,id_evenement) VALUES (?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1,element.getClient().getId());
        statement.setInt(2,element.getEvenement().getId());
        int rows = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            element.setId(resultSet.getInt(1));
        }
        return rows == 1;
    }

    @Override
    public boolean update(Billet element) throws ExecutionControl.NotImplementedException{
        throw new ExecutionControl.NotImplementedException("billet");
    }

    @Override
    public boolean delete(Billet element) throws SQLException {
        String requestEvent = "SELECT (evenement.id) as id_event, nbr_billet_vendu FROM billet INNER JOIN evenement ON billet.id_evenement = evenement.id WHERE billet.id = ?";
        Evenement event = null;
        statement = _connection.prepareStatement(requestEvent);
        statement.setInt(1,element.getId());
        resultSet = statement.executeQuery();
        if(resultSet.next()){
            event = new Evenement(resultSet.getInt("id_event"),resultSet.getInt("nbr_billet_vendu"));
        }
        if(event != null){
            event.annulationTicket();
            EvenementDAO evenementDAO = new EvenementDAO(_connection);
            evenementDAO.updateNbrTicket(event);
            request = "DELETE FROM billet WHERE id = ?";
            statement = _connection.prepareStatement(request);
            statement.setInt(1,element.getId());
            int rows = statement.executeUpdate();
            return rows == 1;
        }
       return false;
    }

    @Override
    public List<Billet> findAll() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("billet");
    }

    @Override
    public Billet findById(int id) throws SQLException {
        Billet billet = null;
        request = "SELECT b.id as id_billet,b.id_client as id_client , b.id_evenement as id_evenement FROM billet as b INNER JOIN clients as c ON b.id_client = c.id INNER JOIN evenement as e ON b.id_evenement = e.id WHERE b.id = ?;";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        if (resultSet.next()){
            EvenementDAO evenementDAO = new EvenementDAO(_connection);
            Evenement evenement = evenementDAO.findById(resultSet.getInt("id_evenement"));
            ClientDao clientDao = new ClientDao(_connection);
            Client client = clientDao.findById(resultSet.getInt("id_client"));
            billet = new Billet(id,client,evenement);

        }
        return billet;
    }

    public List<Billet> findAllByCLientID (int idClient) throws SQLException{
        List<Billet> billets = new ArrayList<>();
        request = "SELECT (billet.id)as id_billet,id_evenement from billet WHERE id_client = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,idClient);
        resultSet = statement.executeQuery();
        ClientDao clientDao = new ClientDao(_connection);
        Client client = clientDao.findById(idClient);
        while (resultSet.next()){
            Connection connection = new DatabaseManager().getConnection();
            EvenementDAO evenementDAO = new EvenementDAO(connection);
            Evenement event = evenementDAO.findById(resultSet.getInt("id_evenement"));
            if(client != null && event != null){
                billets.add(new Billet(resultSet.getInt("id_billet"),client,event));
            }
        }
        return billets;
    }
}
