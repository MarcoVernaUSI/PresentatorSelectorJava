package com.dbMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.FakeResultSet;
import com.model.Seminar;
import com.model.Student;


public class SeminarMapper implements DbMapper<Seminar>{
    
    private final static String TableName = "Seminar";
    private final Connection _connection;
    
    public SeminarMapper(Connection connection) {
        _connection = connection;
    }
    
    
    @Override
    public Iterable<Seminar> findAll(){
        
        try {
            return read(_connection.prepareStatement("select * from "+TableName).executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public Seminar findById(String id) {
        try {
            PreparedStatement ps = _connection.prepareStatement("select * from "+TableName+" where id = ?");
            ps.setObject(1, id);
            
            return read(ps.executeQuery()).get(0);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    @Override
    public List<Seminar> read(ResultSet rs) {
        
        List<Seminar> entries = new ArrayList<Seminar>();            
        try {
            if (rs instanceof FakeResultSet) {
                return (List<Seminar>) rs.unwrap(Seminar.class);    
            }
            while(rs.next()){
                Seminar seminar = new Seminar(
                    rs.getInt(1),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(2), 
                    rs.getString(3),
                    rs.getString(6));
                entries.add(new StudentMapper(_connection).addStudentsTo(seminar));          
            }       
     
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entries;
    }
    
    @Override 
    public void update(Seminar seminar, String id) {
        PreparedStatement ps;
        try {
            ps = _connection.prepareStatement("update seminar set name = ?,description = ?,location = ?,totalSeats = ?,start = ? where id = ?"); 
            ps.setObject(1, seminar.getName());
            ps.setObject(2, seminar.getDescription());
            ps.setObject(3, seminar.getLocation());
            ps.setObject(4, seminar.getTotalSeats());
            ps.setObject(5, seminar.getStartDate());
            ps.setObject(6, id);
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int insert(Seminar seminar) {
        PreparedStatement ps;
        try {
            ps = _connection.prepareStatement("insert into "+TableName+" (name, description, location, totalSeats, start) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS); 
            ps.setObject(1, seminar.getName());
            ps.setObject(2, seminar.getDescription());
            ps.setObject(3, seminar.getLocation());
            ps.setObject(4, seminar.getTotalSeats());
            ps.setObject(5, seminar.getStartDate());
            ps.executeUpdate();

            int seminarId = ps.getGeneratedKeys().getInt(1);

            ps.close();
            
            for (Student student : seminar.getStudentsList()) {
                int studentId = new StudentMapper(_connection).getStudentId(student);
                
                if (studentId == -1){
                    studentId =new StudentMapper(_connection).insert(student);        
                }
                
                PreparedStatement  ps2 = _connection.prepareStatement("insert into Enrollement (studentId, seminarId ) values (?,?)");
                ps2.setObject(1, studentId);
                ps2.setObject(2, seminarId);
                ps2.executeUpdate();
                ps2.close();    
            }
            return seminarId;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // qui dovrei cancellare anche tutte le connessioni in enrollement
    @Override
    public void delete(String id) {
        PreparedStatement ps;
        try {
            ps = _connection.prepareStatement("delete from "+TableName+" where id = ?");
            ps.setObject(1, id);
            ps.executeUpdate();
            ps.close();
            
            ps = _connection.prepareStatement("delete from Enrollement where seminarId = ?");
            ps.setObject(1, id);
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
