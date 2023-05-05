package dao;

import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class BaseDAO<T> {
    protected Connection _connection;
    protected String request;
    protected PreparedStatement statement;
    protected ResultSet resultSet;

    public BaseDAO(Connection connection) {
        _connection = connection;
    }

    public abstract boolean save (T element) throws ExecutionControl.NotImplementedException , SQLException;
    public abstract boolean update (T element) throws ExecutionControl.NotImplementedException , SQLException;
    public abstract boolean delete (int id) throws ExecutionControl.NotImplementedException , SQLException;
    public abstract List<T> findAll () throws ExecutionControl.NotImplementedException , SQLException;
    public abstract T findById (int id) throws ExecutionControl.NotImplementedException , SQLException;
}
