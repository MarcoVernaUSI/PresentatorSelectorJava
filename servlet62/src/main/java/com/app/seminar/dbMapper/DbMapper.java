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

    public DbMapper(Connection connection, DbReader<T> dbReader) {
        _connection = connection;
        _dbReader = dbReader;
    }
    
    public Iterable<T> findAll() {
        try {
            // qui cambio nome tabella da corso a seminario
            PreparedStatement preparedStatement = _connection.prepareStatement("select * from course");
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
            PreparedStatement ps = _connection.prepareStatement("select * from Course where id = ?");
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
}
