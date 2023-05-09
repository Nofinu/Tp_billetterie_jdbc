package dao;

import jdk.jshell.spi.ExecutionControl;
import model.Billet;
import model.Client;
import model.Lieu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDao extends BaseDAO<Client> {

    public ClientDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Client element) throws SQLException {
        request ="INSERT INTO clients(nom,prenom,email) VALUES (?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,element.getNom());
        statement.setString(2,element.getPrenom());
        statement.setString(3,element.getEmail());
        int rows = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            element.setId(resultSet.getInt(1));
        }
        return rows ==1;
    }

    @Override
    public boolean update(Client element) throws SQLException {
        request = "UPDATE clients set nom = ?, prenom = ?,email = ? WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1,element.getNom());
        statement.setString(2, element.getPrenom());
        statement.setString(3,element.getEmail());
        statement.setInt(4,element.getId());
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String requestBillet = "SELECT (billet.id)as id_billet FROM billet WHERE id_client = ?";
        statement = _connection.prepareStatement(requestBillet);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        while (resultSet.next()){
            BilletDAO billetDAO = new BilletDAO(_connection);
            billetDAO.delete(resultSet.getInt("id_billet"));
        }

        request = "DELETE FROM clients WHERE id =?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,id);
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    @Override
    public List<Client> findAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        request = "SELECT id,nom,prenom,email FROM clients";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()){
            clients.add(new Client(resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("email")));
        }
        return clients;
    }

    @Override
    public Client findById(int id) throws SQLException {
        Client client = null;
        request = "SELECT id,nom,prenom,email FROM clients WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        if(resultSet.next()){
            client = new Client(resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("email"));
        }
        return client;
    }
}


