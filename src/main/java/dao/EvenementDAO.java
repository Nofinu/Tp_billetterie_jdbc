package dao;

import jdk.jshell.spi.ExecutionControl;
import model.Client;
import model.Evenement;
import model.Lieu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EvenementDAO extends BaseDAO<Evenement> {

    public EvenementDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Evenement element) throws SQLException {
        request = "INSERT INTO evenement(nom,date,heure,id_lieu,prix,nbr_billet_vendu) VALUES (?,?,?,?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,element.getNom());
        statement.setDate(2, new java.sql.Date(element.getDate().getTime()));
        statement.setString(3,element.getHeure());
        statement.setInt(4,element.getLieu().getId());
        statement.setFloat(5,element.getPrix());
        statement.setInt(6,element.getNbrBilletVendu());
        int rows = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if (resultSet.next()){
            element.setId(resultSet.getInt(1));
        }
        return rows == 1;
    }

    @Override
    public boolean update(Evenement element) throws SQLException {
        request ="UPDATE evenement SET nom =?, date = ?, heure = ?, id_lieu = ?, prix = ?, nbr_billet_vendu =? WHERE id =?";
        statement = _connection.prepareStatement(request);
        statement.setString(1,element.getNom());
        statement.setDate(2, new java.sql.Date(element.getDate().getTime()));
        statement.setString(3,element.getHeure());
        statement.setInt(4, element.getLieu().getId());
        statement.setFloat(5,element.getPrix());
        statement.setInt(6,element.getNbrBilletVendu());
        statement.setInt(7,element.getId());
        int rows = statement.executeUpdate();
        return rows == 1;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        request = "DELETE FROM evenement WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,id);
        int rows = statement.executeUpdate();
        return rows ==1;
    }

    @Override
    public List<Evenement> findAll() throws SQLException {
        List<Evenement> evenements = new ArrayList<>();
        request = "SELECT id,nom,date,heure,id_lieu,prix,nbr_billet_vendu,(lieu.nom) as nom_lieu,adresse,capacite FROM evenement INNER JOIN lieu";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()){
            evenements.add(new Evenement(resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getDate("date"),
                    resultSet.getString("heure"),
                    new Lieu(resultSet.getInt("id_lieu"),
                            resultSet.getString("nom_lieu"),
                            resultSet.getString("adresse"),
                            resultSet.getInt("capacite")),
                    resultSet.getFloat("prix"),
                    resultSet.getInt("nbr_billet_vendu")
                    ));
        }
        return evenements;
    }

    @Override
    public Evenement findById(int id) throws SQLException {
        Evenement evenement = null;
        request = "SELECT id,nom,date,heure,id_lieu,prix,nbr_billet_vendu,(lieu.nom) as nom_lieu,adresse,capacite FROM evenement INNER JOIN lieu WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        if (resultSet.next()){
            evenement = new Evenement(resultSet.getInt("id"),
                    resultSet.getString("nom"),
                    resultSet.getDate("date"),
                    resultSet.getString("heure"),
                    new Lieu(resultSet.getInt("id_lieu"),
                            resultSet.getString("nom_lieu"),
                            resultSet.getString("adresse"),
                            resultSet.getInt("capacite")),
                    resultSet.getFloat("prix"),
                    resultSet.getInt("nbr_billet_vendu")
            );
        }
        return evenement;
    }


}
