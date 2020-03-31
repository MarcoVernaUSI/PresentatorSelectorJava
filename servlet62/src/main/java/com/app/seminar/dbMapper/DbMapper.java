package com.app.seminar.dbMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbMapper<T>  {
    private final Connection _connection;
    private final DbReader<T> _dbReader;
    private final String _tableName; 

    public DbMapper(Connection connection, DbReader<T> dbReader, String tableName) {
        _connection = connection;
        _dbReader = dbReader;
        _tableName = tableName;
    }
    
    public Iterable<T> findAll() {
        try {
            // qui cambio nome tabella da corso a seminario
            PreparedStatement preparedStatement = _connection.prepareStatement("select * from "+_tableName);
            ResultSet rs = preparedStatement.executeQuery();
            List<T> entries = new ArrayList<T>();
            while(rs.next()){
                entries.add(_dbReader.read(rs));
            }
            preparedStatement.close();
            rs.close();
            return entries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public T findById(String id) {
        try {
            PreparedStatement ps = _connection.prepareStatement("select * from "+_tableName+" where id = ?");
            ps.setObject(1, id);
            
            ResultSet rs = ps.executeQuery();
            List<T> entries = new ArrayList<T>();
            while(rs.next()) {
                entries.add(_dbReader.read(rs));
            }
            ps.close();
            rs.close();
            return entries.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void insert(T entry){
        try {
            PreparedStatement ps = _dbReader.write(_connection, entry);
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void save(T entry){
            insert(entry);
    }
    
    
    public void create() {
        try {
            PreparedStatement ps = _connection.prepareStatement("CREATE TABLE Seminar(" + 
                "id INTEGER PRIMARY KEY," + 
                "name VARCHAR(255) NOT NULL," + 
                "description VARCHAR(255)," + 
                "location VARCHAR(255) NOT NULL," + 
                "totalSeats NUMERIC NOT NULL," + 
                "start DATETIME NOT NULL)");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
}
